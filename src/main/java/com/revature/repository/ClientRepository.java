package com.revature.repository;

import java.util.Set;

import com.revature.model.Client;

public interface ClientRepository {
		// will need to fill up with all possible pulls from the database in regards to client
		
			/**
			 * insert a new client in the database
			 * @param celebrity
			 * @return
			 */
		public Client findByUsernameAndPassword(String username, String password);
			
			/**
			 * update a client in the database
			 * 
			 * @param celebrity
			 * @return
			 */
		public boolean updateWithdrawel(String username, double newBalance);
		
		public boolean updateDeposit(String username, double newBalance);

		public Set<Client> selectAll();
}


