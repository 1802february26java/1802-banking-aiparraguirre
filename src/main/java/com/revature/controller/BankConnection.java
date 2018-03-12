package com.revature.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class BankConnection {

	private static Logger logger= Logger.getLogger(BankConnection.class);
		
	public static Connection getConnection() throws SQLException {
		
		String url = "jdbc:oracle:thin:@bank.ckmmgczymsq9.us-east-1.rds.amazonaws.com:1521:ORCL";
		String username = "BANK_ADMIN";
		String password = "PASS";
			
		return DriverManager.getConnection(url, username, password);
	}
		
	public static void main(String[] args) {
			
		try(Connection connection = BankConnection.getConnection()){
			logger.info("Connection Successful");
		}catch(SQLException e){
			logger.error("Couldn't connect to the database",e);
		}
	}
}

	

