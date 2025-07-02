package api.transactions.dsw2.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import api.transactions.dsw2.model.entity.Transaction;
import jakarta.servlet.ServletException;

public class TransactionDao {

	private DataSource dataSource;
	
	private static final String INSERT_TRANSACTION = "INSERT INTO transaction (description, value, type, category, date) VALUES (?, ?, ?, ?, ?)";
	
	public TransactionDao() throws NamingException{
		InitialContext context = new InitialContext();
		dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TransactionAPI");
	}
	
	public boolean insert(Transaction transaction)throws ServletException, SQLException{
		int rows = 0;
		
		if(transaction != null) {
			try(Connection connection = dataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(INSERT_TRANSACTION)){
				
				statement.setString(1, transaction.getDescription());
				statement.setDouble(2, transaction.getValue());
				statement.setString(3, transaction.getType().name());
				statement.setString(4, transaction.getCategory());
				statement.setString(5, transaction.getDate());
				
				rows = statement.executeUpdate();
			
			}
			
		}
		
		return rows > 0;
	}	
	
}
