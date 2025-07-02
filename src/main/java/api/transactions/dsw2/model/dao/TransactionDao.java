package api.transactions.dsw2.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	
	public TransactionDao() throws NamingException{
		InitialContext context = new InitialContext();
		dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TransactionAPI");
	}
	
	public void insert(Transaction transaction)throws SQLException{
		
		if(transaction != null) {
			try(Connection connection = dataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, transaction.getDescription());
				statement.setDouble(2, transaction.getValue());
				statement.setString(3, transaction.getType().name());
				statement.setString(4, transaction.getCategory());
				statement.setString(5, transaction.getDate());
				
				statement.executeUpdate();
			}
		}
	}	
	
	public Transaction find_by_id(int id) throws SQLException {
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)){
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				Transaction transaction = new Transaction();
				
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
	
	public boolean delete(int id) throws SQLException{
		int rows = 0;
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE)){
			
			statement.setInt(1, id);
			rows = statement.executeUpdate();
		}
		
		return rows > 0;
	}
	
	public boolean update(int id, Transaction transaction) throws SQLException{
		int rows = 0;
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)){
			
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
	
}
