package com.zelic.TestProject.repositories;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.User;

public interface AccountsRepositoryCustom {

	Iterable<User> getUsersOnAccount(Long accountId);
	
	User getOwnerOfAccount(Long accountId);
	
	Iterable<Account> getAccountsbyUserAndOwner(Long userId, boolean isOwner);
}
