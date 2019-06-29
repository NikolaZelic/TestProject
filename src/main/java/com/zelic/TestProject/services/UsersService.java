package com.zelic.TestProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.Farm;
import com.zelic.TestProject.entities.User;
import com.zelic.TestProject.repositories.AccountsRepository;
import com.zelic.TestProject.repositories.UsersRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;


@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private AccountsRepository accountsRepository;
	
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
	
	public User getUser(Long userId) {
		return usersRepository.findOne(userId);
	}
	
	/**
	 * Return accounts that user have access to (without accounts that user owns)
	 */
	public Iterable<Account> getUserAvailableAccounts(Long userId) {
		return accountsRepository.getAccountsbyUserAndOwner(userId, false);
	}
	
	/**
	 * Returns accounts that user owns
	 */
	public Iterable<Account> getUserAccounts(Long userId) {
		return accountsRepository.getAccountsbyUserAndOwner(userId, true);
	}
	
	public Iterable<Farm> getUserFarms(Long userId) {
		return usersRepository.getUserFarms(userId);
	}
}

class UserCredentialException extends RuntimeException {
	public UserCredentialException(String message) {
		super(message);
	}
}
