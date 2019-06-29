package com.zelic.TestProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.User;
import com.zelic.TestProject.repositories.UsersRepository;
import com.zelic.TestProject.services.CustomersService;


/**
 * Customer is a user with a at least one account.
 */
@RestController
@RequestMapping("/api/customers")
public class CustomersController {

	@Autowired
	private CustomersService customersService;
	
	@GetMapping
	public Iterable<User> getCustomers() {
		return customersService.getCustomers();
	}
	
	@RequestMapping("/{id}/accounts")
	public Iterable<Account> getCustumerAccounts(@PathVariable Long id) {
		return customersService.getCustomerAccounts(id);
	}
	
}
