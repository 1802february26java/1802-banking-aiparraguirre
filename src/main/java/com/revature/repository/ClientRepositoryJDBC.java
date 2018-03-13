package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.controller.BankConnection;
import com.revature.model.Client;

public class ClientRepositoryJDBC implements ClientRepository{

	private static Logger logger = Logger.getLogger(ClientRepositoryJDBC.class);
	
	private static ClientRepositoryJDBC repository = new ClientRepositoryJDBC();
	
	public ClientRepositoryJDBC(){}
	
	public static ClientRepository getInstance(){
		return repository;
	}
	
	public Client findByUsernameAndPassword(String username, String password){
		
		try(Connection connection = BankConnection.getConnection()){
				
			int parameterIndex = 0;
			
			String sql="SELECT * FROM CLIENT WHERE C_USERNAME = ? AND C_PASSWORD = ?";
			
			logger.trace("Getting CLIENT by username and password.");
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(++parameterIndex, username);
			statement.setString(++parameterIndex, password);

			logger.trace("after setting setString to statement.");
			ResultSet result = statement.executeQuery();
									
			while(result.next()){	
				
				return new Client(
						result.getInt("C_ID"),
						result.getString("C_FNAME"),
						result.getString("C_LNAME"),
						result.getString("C_USERNAME"),
						result.getString("C_PASSWORD"),
						result.getDouble("C_BALANCE")
						);
			}
			
			}catch(SQLException e){
				logger.error("Error while selecting client by credentials.", e);
			}
			
		return null;
	}	
	
	

	@Override
	public boolean updateBalance(String username, double newBalance) {
		try(Connection connection = BankConnection.getConnection()){
			
			int parameterIndex = 0;
			
			String sql="UPDATE CLIENT SET C_BALANCE = ? WHERE C_USERNAME = ?";
			
			logger.trace("Getting CLIENT by username and password.");
			PreparedStatement statement = connection.prepareStatement(sql);
	
			statement.setDouble(++parameterIndex, newBalance);
			statement.setString(++parameterIndex, username);

			statement.executeQuery();	
				
		}catch(SQLException e){
			logger.error("Error while selecting client by credentials.", e);
		}
			
		logger.trace("returning null");
		return false;
	}	

	public Set<Client> selectAll(){
		try(Connection connection = BankConnection.getConnection()){
			String sql="SELECT * FROM CLIENT";
			
			logger.trace("Getting statement object in get all CLIENT");
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			Set<Client> set = new HashSet<>();
			
			while(result.next()){
				
				set.add(new Client(
						result.getInt("C_ID"),
						result.getString("C_FNAME"),
						result.getString("C_LNAME"),
						result.getString("C_USERNAME"),
						result.getString("C_PASSWORD"),
						result.getDouble("C_BALANCE")
						));
			
			}
			
			return set;
		}catch(SQLException e){
			logger.error("Error while selecting all clients", e);
		}
		return null;
	}
	
}


	
		

