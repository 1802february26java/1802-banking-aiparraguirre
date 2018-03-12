package com.revature.service;

import java.util.Scanner;

import com.revature.model.Client;

public class Console implements Verification {

	
	@Override
	public void checkCredentials() {
		// to call the select from the repository and pass in specific values and get the table if true or return false if user is missing 
		String username;
		String password;
		Client client = new Client();
		Scanner scanner = new Scanner(System.in);
		
		try{
		
		System.out.println("Please log in.");
		System.out.print("Username:");
		username=scanner.nextLine();
		System.out.println("-------------------------------------");
		System.out.print("Password:");
		System.out.println();
		password = scanner.nextLine();
		System.out.println("--------------Checking---------------");
		
		client.setUsername(username);
		client.setPassword(password);
		
		}finally{
			scanner.close();
		}
		
		System.out.println("username: " + client.getUsername() 
					+ " password: " + client.getPassword());
		
	}

	@Override
	public void checkBalance() {
		// will use the select method and pull the balance depending the user account
		
	}

	@Override
	public void makeDeposit() {
		// increment the balance according to the amount made will need to call the update method
		
	}

	@Override
	public void makeWithdrawel() {
		// same as the make deposit except we will decrement the account
		
	}

public static void main(String[] args) {
	Console console = new Console();
	console.checkCredentials();
}
}

