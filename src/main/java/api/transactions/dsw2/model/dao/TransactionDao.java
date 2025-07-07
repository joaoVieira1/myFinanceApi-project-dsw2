package api.transactions.dsw2.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import api.transactions.dsw2.model.entity.Transaction;
import api.transactions.dsw2.model.entity.TransactionType;
import jakarta.servlet.ServletException;

public class TransactionDao {

	private DataSource dataSource;

	private static final String INSERT = "INSERT INTO transaction (description, value, type, category, date) VALUES (?, ?, ?, ?, ?)";
	private static final String FIND_BY_ID = "SELECT * FROM transaction WHERE id = ?";
	private static final String DELETE = "DELETE FROM transaction WHERE id = ?";
	private static final String UPDATE = "UPDATE transaction SET description = ?, value = ?, type = ?, category = ?, date = ? WHERE id = ?";

	public TransactionDao() throws NamingException {
		InitialContext context = new InitialContext();
		dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TransactionAPI");
	}

	public void insert(Transaction transaction) throws SQLException {

		if (transaction != null) {
			try (Connection connection = dataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(INSERT)) {

				statement.setString(1, transaction.getDescription());
				statement.setDouble(2, transaction.getValue());
				statement.setString(3, transaction.getType().name());
				statement.setString(4, transaction.getCategory());
				statement.setString(5, transaction.getDate());

				statement.executeUpdate();
			}
		}
	}

	public Transaction findById(int id) throws SQLException {

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				Transaction transaction = new Transaction();
				
				transaction.setId(result.getInt("id"));
				transaction.setDescription(result.getString("description"));
				transaction.setValue(result.getDouble("value"));
				String typeString = result.getString("type");
				TransactionType type = TransactionType.valueOf(typeString);
				transaction.setType(type);
				transaction.setCategory(result.getString("category"));
				transaction.setDate(result.getString("date"));

				result.close();

				return transaction;
			}

		}

		return null;
	}

	public boolean delete(int id) throws SQLException {
		int rows = 0;

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE)) {

			statement.setInt(1, id);
			rows = statement.executeUpdate();
		}

		return rows > 0;
	}

	public boolean update(int id, Transaction transaction) throws SQLException {
		int rows = 0;

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)) {

			statement.setString(1, transaction.getDescription());
			statement.setDouble(2, transaction.getValue());
			statement.setString(3, transaction.getType().name());
			statement.setString(4, transaction.getCategory());
			statement.setString(5, transaction.getDate());
			statement.setInt(6, id);

			rows = statement.executeUpdate();
		}

		return rows > 0;
	}

	public List<Transaction> findAllWithFilters(Integer month, Integer year, TransactionType type, String category,int offset, int limit) throws SQLException {
		List<Transaction> list = new ArrayList<>();

		StringBuilder sql = new StringBuilder("SELECT * FROM transaction WHERE 1=1");

		if (month != null)
			sql.append(" AND MONTH(STR_TO_DATE(date, '%Y-%m-%d')) = ?");
		if (year != null)
			sql.append(" AND YEAR(STR_TO_DATE(date, '%Y-%m-%d')) = ?");
		if (type != null)
			sql.append(" AND type = ?");
		if (category != null && !category.isBlank())
			sql.append(" AND category = ?");

		sql.append(" ORDER BY date DESC LIMIT ? OFFSET ?");

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql.toString())) {

			int index = 1;

			if (month != null)
				statement.setInt(index++, month);
			if (year != null)
				statement.setInt(index++, year);
			if (type != null)
				statement.setString(index++, type.name());
			if (category != null && !category.isBlank())
				statement.setString(index++, category);

			statement.setInt(index++, limit);
			statement.setInt(index++, offset);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(result.getInt("id"));
				transaction.setDescription(result.getString("description"));
				transaction.setValue(result.getDouble("value"));
				transaction.setType(TransactionType.valueOf(result.getString("type")));
				transaction.setCategory(result.getString("category"));
				transaction.setDate(result.getString("date"));
				list.add(transaction);
			}
		}

		return list;
	}
	
	public Map<String, Double> sumByCategory(TransactionType type) throws SQLException {
	    Map<String, Double> results = new LinkedHashMap<>();

	    String sql = "SELECT category, SUM(value) as total " +
	                 "FROM transaction WHERE type = ? GROUP BY category";

	    try (Connection connection= dataSource.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, type.name());

	        ResultSet result = statement.executeQuery();
	        while (result.next()) {
	            String category = result.getString("category");
	            double total = result.getDouble("total");
	            results.put(category, total);
	        }
	    }

	    return results;
	}
	
}
