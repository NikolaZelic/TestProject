package com.zelic.TestProject.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zelic.TestProject.entities.User;

@Repository
@Transactional(readOnly = true)
public class UsersRepositoryImpl implements UsersRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Iterable<User> getCustomers() {
		String sql = "SELECT u FROM UserAccount ua JOIN ua.user u WHERE ua.isOwner = 1";
		Query query = entityManager.createQuery(sql, User.class);
		return query.getResultList();
	}

	
	
}
