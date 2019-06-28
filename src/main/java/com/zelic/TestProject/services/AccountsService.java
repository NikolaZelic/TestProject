package com.zelic.TestProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.repositories.AccountsRepository;

@Service
public class AccountsService {

	@Autowired
	private AccountsRepository accountsRepository;
	
	public Iterable<Account> getAccounts() {
		return accountsRepository.findAll();
	}
	
	public Long createAccount(Account account) {
		return accountsRepository.save(account).getId();
	}
	
	
}
