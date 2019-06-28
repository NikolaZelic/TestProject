package com.zelic.TestProject.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.User;
import com.zelic.TestProject.entities.UserAccount;
import com.zelic.TestProject.entities.UserAccountId;
import com.zelic.TestProject.repositories.AccountsRepository;
import com.zelic.TestProject.repositories.UsersAccountsRepository;
import com.zelic.TestProject.repositories.UsersRepository;

@Service
public class AccountsService {

	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired 
	private UsersRepository usersRepository;
	@Autowired
	private UsersAccountsRepository usersAccountsRepository;
	
	public Iterable<Account> getAccounts() {
		return accountsRepository.findAll();
	}
	
	public Long createAccount(Account account) {
		return accountsRepository.save(account).getId();
	}
	
	public Account getAccount(Long id) {
		return accountsRepository.findOne(id);
	}
	
	/**
	 * Return users that have access to account (excluding owner)
	 */
	public Iterable<User> getUsersOnAccount(Long accountId) {
		Account account = accountsRepository.findOne(accountId);
		if(account==null)
			return null;
		// TODO Ako stignes odradi ovo preko jednog upita
		return account.getUsers().
				stream().
				filter( element -> !element.getIsOwner() ).
				map( element -> element.getUser() ).
				collect( Collectors.toList() );
	}
	
	public Optional<User> getOwnerOfAccount(Long accountId) {
		Account account = accountsRepository.findOne(accountId);
		if(account==null)
			return null;
		return account.getUsers().
				stream().
				filter( element -> element.getIsOwner() ).
				map( el -> el.getUser() ).
				findFirst();
	}
	
	public boolean addUserToAccount(Long accountId, Long userId) {
		Account account = accountsRepository.findOne(accountId);
		if(account==null) {	// TODO Pronaci koji se izuzeci ispaljuju u ovakvim situacijama
			throw new IllegalArgumentException("There isn't account with ID: " + accountId);
		}
		
		User user = usersRepository.findOne(userId);
		if(user==null) {	// TODO Pronaci koji se izuzeci ispaljuju u ovakvim situacijama
			throw new IllegalArgumentException("There isn't user with ID: " + userId);
		}
		
		if(usersAccountsRepository.findOne(new UserAccountId(userId, accountId))!=null)
			return false;	// User already has access to account
		
		UserAccount userAccount = new UserAccount(user, account, false);
		usersAccountsRepository.save(userAccount);
		return true;	// Granted access to users
	}
}
