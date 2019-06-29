package com.zelic.TestProject.repositories;

import com.zelic.TestProject.entities.Farm;
import com.zelic.TestProject.entities.User;

public interface UsersRepositoryCustom {

	Iterable<User> getCustomers();
	
	Iterable<Farm> getUserFarms(Long userId);
	
}
