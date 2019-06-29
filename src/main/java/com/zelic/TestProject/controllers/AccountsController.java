package com.zelic.TestProject.controllers;

import java.util.Objects;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.User;
import com.zelic.TestProject.services.AccountsService;

import errors.NoSessionException;

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
	public Long createAccount(@Valid @RequestBody Account account, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if( session.isNew() || Objects.isNull(user) )
			throw new NoSessionException();
		
		return accountsService.createAccount(account, user);
	}
	
	@GetMapping("/{id}")
	public Account getAccount( @PathVariable Long id ) {
		return accountsService.getAccount(id);
	}
	
	@GetMapping("/{id}/users")
	public Iterable<User> getUsersOnAccount(@PathVariable Long id) {
		return accountsService.getUsersOnAccount(id);
	}
	
	@GetMapping("/{id}/customer")
	public User getOwnerOfAccount(@PathVariable Long id) {
		return accountsService.getOwnerOfAccount(id);
	}
	
	@PostMapping("/{id}/users")
	public boolean addUserToAccount(@PathVariable Long id, @RequestParam Long userId) {
		System.out.println(id+" "+userId);
		return accountsService.addUserToAccount(id, userId);
	}
	
}
