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
	
	
	public Client findByUsernameAndPassword(String username, String password){
		
		try(Connection connection = BankConnection.getConnection()){
				
			int parameterIndex = 0;
			
			String sql="SELECT * FROM CLIENT WHERE C_USERNAME = ? AND C_PASSWORD = ?";
			
			logger.trace("Getting CLIENT by username and password.");
			PreparedStatement statement = connection.prepareStatement(sql);
	
			logger.trace(username +"  " + password);
			
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
			
		logger.trace("returning null");
		return null;
	}	
	
	/*
	public boolean updateBalance(String username, String password, double value){
		try(Connection connection = BankConnection.getConnection()){
			int parameterIndex = 0;
			// write this part first (under this comment)
			
			String sql = "UPDATE CLIENT SET C_BALANCE = ? WHERE C_USERNAME = ? AND C_PASSWORD = ?"; 
			
			logger.trace("Updating Account Balance.");
			
			// now we can get the prepared statement object from the connection object
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(++parameterIndex, client.getC_id());
			statement.setString(++parameterIndex, client.getfName());
			statement.setString(++parameterIndex, client.getlName());
			statement.setInt(++parameterIndex, client.getAccountId());
			statement.setString(++parameterIndex, client.getUsername());
			statement.setString(++parameterIndex, client.getPassword());
			
			
			logger.trace("Parameters for insertion of Celebrity set.");
			if(statement.executeUpdate()>0){
				logger.trace("Celebrity was inserted into table.");
				return true;
			}
							
		}catch(SQLException e){
			logger.error("Exception thrown while inserting data",e);
		}
	
	
		return false;
		
	}

*/
	@Override
	public boolean updateWithdrawel(String username, double newBalance) {
		try(Connection connection = BankConnection.getConnection()){
			
			int parameterIndex = 0;
			
			String sql="UPDATE CLIENT SET BALANCE = ? WHERE C_USERNAME = ?";
			
			logger.trace("Getting CLIENT by username and password.");
			PreparedStatement statement = connection.prepareStatement(sql);
	
			statement.setDouble(++parameterIndex, newBalance);
			statement.setString(++parameterIndex, username);

			logger.trace("after setting setString to statement.");
			statement.executeQuery();	
				
		}catch(SQLException e){
			logger.error("Error while selecting client by credentials.", e);
		}
			
		logger.trace("returning null");
		return false;
	}	

	
	public boolean updateDeposit(String username, double amount){
		
		return false;
		
	}
	
	public Set<Client> selectAll(){
		try(Connection connection = BankConnection.getConnection()){
			String sql="SELECT * FROM CLIENT";
			
			logger.trace("Getting statement object in get all CLIENT");
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();// returns a result set object
			
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
			// return set
			return set;
		}catch(SQLException e){
			logger.error("Error while selecting all celebrities", e);
		}
		return null;
}
	

	public static void main(String[] args) {
		ClientRepository repository = new ClientRepositoryJDBC();// instance
		logger.info(repository.selectAll());
		logger.info(repository.findByUsernameAndPassword("SONMAC86","1234"));
	
	}
}


	
		

