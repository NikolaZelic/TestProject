package com.zelic.TestProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.services.AccountsService;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

	@Autowired
	private AccountsService accountsService;
	
	@GetMapping
	public Iterable<Account> getAccounts() {
		return accountsService.getAccounts();
	}
	
	@PostMapping
	public Long createAccount(@RequestBody Account account) {
		return accountsService.createAccount(account);
	}
	
}
