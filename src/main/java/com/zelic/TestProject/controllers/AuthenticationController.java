package com.zelic.TestProject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zelic.TestProject.entities.User;
import com.zelic.TestProject.services.UsersService;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

	@Autowired
	private UsersService usersService;
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String register( @Valid @RequestBody User user ) {
		usersService.createUser(user);
		return "Successful registration";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login( @RequestBody LoginDetails details, HttpSession session ) {
		User user = usersService.loginUser(details.getEmail(), details.getPassword());
		session.setAttribute("user", user);
		return "Successful login";
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "User logged out";
	}
	
	@RequestMapping(path = "/checksession", method = RequestMethod.GET)
	public Object getSessionUser(HttpSession session) {
		return session.getAttribute("user");
	}
}

class LoginDetails {
	
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
