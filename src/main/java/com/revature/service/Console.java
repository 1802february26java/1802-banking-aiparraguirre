package com.revature.service;

public class Console implements Verification {

	
	@Override
	public void checkCredentials() {
		// to call the select from the repository and pass in specific values and get the table if true or return false if user is missing 
		
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

}
