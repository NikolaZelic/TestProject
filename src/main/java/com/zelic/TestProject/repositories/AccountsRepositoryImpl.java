package com.zelic.TestProject.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.User;

public class AccountsRepositoryImpl implements AccountsRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Iterable<User> getUsersOnAccount(Long accountId) {
		String sql = "SELECT u FROM UserAccount ua JOIN ua.user u WHERE ua.isOwner = 0 AND ua.id.accountId = ?1";
		Query query = entityManager.createQuery(sql, User.class);
		query.setParameter(1, accountId);
		return query.getResultList();
	}

	@Override
	public User getOwnerOfAccount(Long accountId) {
		String sql = "SELECT u FROM UserAccount ua JOIN ua.user u WHERE ua.isOwner = 1 AND ua.id.accountId = ?1";
		Query query = entityManager.createQuery(sql, User.class);
		query.setParameter(1, accountId);
		List<User> resultList = query.getResultList();
		if(resultList.isEmpty())
			return null;
		return resultList.get(0);
	}

	@Override
	public Iterable<Account> getAccountsbyUserAndOwner(Long userId, boolean isOwner){
		String sql = "SELECT a FROM UserAccount ua JOIN ua.account a WHERE ua.isOwner = ?1 AND ua.id.userId = ?2";
		Query query = entityManager.createQuery(sql, Account.class);
		query.setParameter(1, isOwner? 1:0);
		query.setParameter(2, userId);
		return query.getResultList();
	}
	
}
