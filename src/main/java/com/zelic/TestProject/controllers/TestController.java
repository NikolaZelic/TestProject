package com.zelic.TestProject.controllers;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zelic.TestProject.entities.User;
import com.zelic.TestProject.entities.UserAccount;
import com.zelic.TestProject.repositories.UsersRepository;

@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	private UsersRepository usersRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping
	public Object test() {
		String sql = "SELECT u FROM UserAccount ua JOIN ua.user u WHERE ua.isOwner = 1";
		Query query = entityManager.createQuery(sql, User.class);
		return query.getResultList();
//		String sql = "SELECT * FROM users_accounts WHERE IS_OWNER = TRUE";
//		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
//		return list;
	}
	
	@RequestMapping("/session")
	@GetMapping
	public Object chechSession(HttpSession session) {
		return session.getAttribute("user");
	}
}
