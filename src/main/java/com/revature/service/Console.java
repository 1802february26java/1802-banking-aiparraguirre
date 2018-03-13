package com.revature.service;

import java.util.Scanner;

import com.revature.exception.NoNegativeNumbers;
import com.revature.exception.TooManyLoginAttempts;
import com.revature.model.Client;
import com.revature.repository.ClientRepository;
import com.revature.repository.ClientRepositoryJDBC;

public class Console{

	public static void main(String[] args) {
		
		String username;
		String password;
		
		int attempt = 0;
		int loginSuccessFlag = 0;
		int menuOption;
		double amount;
		double newBalance;
		
		Client client;
		ClientRepository repository = new ClientRepositoryJDBC();
		
		Scanner scanner= new Scanner(System.in);
		
		//---------------Log in set up------------------------------------------------------------
		do{
				
			System.out.println("Please log in.");
			System.out.print("Username:");
			username=scanner.nextLine();
			System.out.print("Password:");
			password = scanner.nextLine();
			System.out.println("--------------Checking---------------");
					
			client = repository.findByUsernameAndPassword(username, password);
			
			if(client == null ){
				System.out.println("Username or Password incorrect.");
				System.out.println("Username and Password case sensitive.");
				System.out.println("----------Please try again-----------");
				++attempt;
				
				if(attempt >3){
					throw new TooManyLoginAttempts("User attempted to Login too many times");
				}
			}else{

				System.out.println("Log in successful");
				loginSuccessFlag = 1;
			}
			
		}while(loginSuccessFlag != 1);
		
		//-----------------Operations to be done to table (Balance)--------------------------------		
		do{
		
		System.out.println("==============================================");
		System.out.println("Menu options:");
		System.out.println("Deposit enter 1");
		System.out.println("Withdrawel enter 2");
		System.out.println("To Log out of your Account enter 3");
		System.out.print("Option:  ");
			
			menuOption=scanner.nextInt();
		
		System.out.println("==============================================");
		
		switch(menuOption){
			case 1:
				System.out.print("How much would you like to deposit?  ");
				amount = scanner.nextDouble();
				

				if(amount < 0){
					
					throw new NoNegativeNumbers("Warning user attempting to input a negative number.");
				}
				
				System.out.println("==============================================");
				
				newBalance = client.getBalance() + amount;
				
				repository.updateBalance(client.getUsername(), newBalance);
				client = repository.findByUsernameAndPassword(username, password);
				
				System.out.println("Account : " + client.getlName() + ", " 
						+ client.getfName() + " | Balance = " + client.getBalance());
				
				break;
			
			case 2:
				
				System.out.print("How much would you like to Withdraw?  ");
				amount = scanner.nextDouble();
				System.out.println("==============================================");
				
				if(amount < 0){
					
					throw new NoNegativeNumbers("Warning user attempting to input a negative number.");
					
				}
				
				newBalance = client.getBalance() - amount;
								
				repository.updateBalance(client.getUsername(), newBalance);
				client = repository.findByUsernameAndPassword(username, password);
				
				System.out.println("Account : " + client.getlName() + ", " 
						+ client.getfName() + " | Balance = " + client.getBalance());
				break;
				
			case 3:
				System.out.println("Thank you for banking with us.");
				break;
		}
		
		}while(menuOption !=3);
				
		scanner.close();
		System.exit(0);
	}

}


