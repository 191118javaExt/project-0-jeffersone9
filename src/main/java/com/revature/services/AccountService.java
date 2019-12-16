package com.revature.services;

import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountDAOImpl;

import java.util.List;
import com.revature.models.Account;

public class AccountService {
	AccountDAO repository = null;
	
	public AccountService() {
		repository = new AccountDAOImpl();
	}
	
	public AccountService(AccountDAOImpl dao) {
		repository = dao;
	}
	
	public List<Account> findAll(){
		return repository.findAllAccounts();
	}
}
