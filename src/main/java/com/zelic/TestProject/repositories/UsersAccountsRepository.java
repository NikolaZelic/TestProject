package com.zelic.TestProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zelic.TestProject.entities.UserAccount;
import com.zelic.TestProject.entities.UserAccountId;

@Repository
public interface UsersAccountsRepository extends CrudRepository<UserAccount, UserAccountId>{

}
