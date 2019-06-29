package com.zelic.TestProject.repositories;

import com.zelic.TestProject.entities.User;

public interface UsersRepositoryCustom {

	Iterable<User> getCustomers();
	
}
