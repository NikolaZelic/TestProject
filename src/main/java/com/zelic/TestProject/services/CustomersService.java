package com.zelic.TestProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.User;
import com.zelic.TestProject.repositories.AccountsRepository;
import com.zelic.TestProject.repositories.UsersRepository;

@Service
public class CustomersService {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private AccountsRepository accountsRepository;
	
	public Iterable<User> getCustomers(){
		return usersRepository.getCustomers();
	}

	public Iterable<Account> getCustomerAccounts(Long id) {
		return accountsRepository.getAccountsbyUserAndOwner(id, true);
	}
}
