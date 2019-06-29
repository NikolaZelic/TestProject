package com.zelic.TestProject.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.Farm;
import com.zelic.TestProject.entities.User;
import com.zelic.TestProject.services.UsersService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	private UsersService usersServices;
	
	@GetMapping
	public Iterable<User> getUsers() {
		return usersServices.getUsers();
	}
	
	@RequestMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return usersServices.getUser(id);
	}
	/**
	 * Returns accounts that user have access to (without accounts that user owns) 
	 */
	@RequestMapping("/{id}/accounts")
	public Iterable<Account> getUserAccounts(@PathVariable Long id) {
		return usersServices.getUserAvailableAccounts(id);
	}
	
	@RequestMapping("/{id}/farms")
	public Iterable<Farm> getUserFarms(@PathVariable Long id) {
		return usersServices.getUserFarms(id);
	}
	
}
