package com.zelic.TestProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zelic.TestProject.entities.User;

@Repository
public interface UsersRepository 
	extends CrudRepository<User, Long>,
	UsersRepositoryCustom
{

	User findByEmail(String email);
	
}
