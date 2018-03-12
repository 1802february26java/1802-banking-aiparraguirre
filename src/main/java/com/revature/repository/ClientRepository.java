package com.revature.repository;

import java.util.Set;

import com.revature.model.Client;

public interface ClientRepository {
	
	public Client findByUsernameAndPassword(String username, String password);
			
	public boolean updateBalance(String username, double newBalance);
		
	public Set<Client> selectAll();
}


