package com.zelic.TestProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelic.TestProject.entities.User;
import com.zelic.TestProject.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	public Long createUser(User user) {
		return usersRepository.save(user).getId();
	}
	
	public Iterable<User> getUsers() {
		return usersRepository.findAll();
	}
}
