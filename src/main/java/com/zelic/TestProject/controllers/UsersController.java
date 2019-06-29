package com.zelic.TestProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
