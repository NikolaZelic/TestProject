package com.zelic.TestProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.Farm;
import com.zelic.TestProject.repositories.AccountsRepository;
import com.zelic.TestProject.repositories.FarmsRepository;

@Service
public class FarmsService {

	
	@Autowired
	private FarmsRepository farmsRepository;
	@Autowired
	private AccountsRepository accountsRepositiry;
	
	public Long createFarmOnAccount(Farm farm, Account account) {
		farm.setId(null);	// Just to be sure
		Farm createdFarm = farmsRepository.save(farm);
		
		account.addFarm(createdFarm);
		accountsRepositiry.save(account);
		
		return createdFarm.getId();
	}
	
	public Iterable<Farm> getFarms() {
		return  farmsRepository.findAll();
	}
}
