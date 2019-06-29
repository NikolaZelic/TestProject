package com.zelic.TestProject.controllers;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.Farm;
import com.zelic.TestProject.services.AccountsService;
import com.zelic.TestProject.services.FarmsService;

import errors.NotFoundException;

@RestController
@RequestMapping("/api/farms")
public class FarmsController {

	
	@Autowired
	private FarmsService farmsService;
	@Autowired
	private AccountsService accountsService;
	
	@GetMapping
	public Iterable<Farm> getFarms() {
		return farmsService.getFarms();
	}
	
	@PostMapping
	public Long createFarm(@Valid @RequestBody Farm farm, @RequestParam Long accountId) {
		Account account = accountsService.getAccount(accountId);
		if(Objects.isNull(account))
			throw new NotFoundException("No account with ID: " + accountId);
		
		return farmsService.createFarmOnAccount(farm, account);
	}
}
