package com.zelic.TestProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelic.TestProject.entities.User;
import com.zelic.TestProject.repositories.UsersRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;


@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
//	BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);

	public Long createUser(User user) {
		user.setId(null);	// Just to be sure that it is new user
		user.setPassword( BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()) );
		return usersRepository.save(user).getId();
	}
	
	public User loginUser(String email, String password) {
		User user = usersRepository.findByEmail(email);
		if(user==null)
			throw new UserCredentialException("Invalid user credential");
		Result verify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
		if(!verify.verified)
			throw new UserCredentialException("Invalid user credential");
		return user;
	}
	
	public Iterable<User> getUsers() {
		return usersRepository.findAll();
	}
	
}

class UserCredentialException extends RuntimeException {
	public UserCredentialException(String message) {
		super(message);
	}
}
