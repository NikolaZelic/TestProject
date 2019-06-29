package com.zelic.TestProject.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zelic.TestProject.repositories.UsersRepository;

@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping
	public Object test() {
//		throw new UserNotFounrException();
		return usersRepository.findByEmail("nzelic@ymail.com");
	}
	
	@RequestMapping("/session")
	@GetMapping
	public Object chechSession(HttpSession session) {
		return session.getAttribute("user");
	}
}
