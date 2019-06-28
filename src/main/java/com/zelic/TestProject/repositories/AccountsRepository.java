package com.zelic.TestProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zelic.TestProject.entities.Account;

@Repository
public interface AccountsRepository 
	extends CrudRepository<Account, Long> {

}
