package com.revature.services;

import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountDAOImpl;

import java.util.List;
import java.util.Map;

import com.revature.models.Account;

public class AccountService {
	AccountDAO repository = null;
	
	public AccountService() {
		repository = new AccountDAOImpl();
	}
	
	public AccountService(AccountDAOImpl dao) {
		repository = dao;
	}
	
	public Map<Integer, List<Account>> findAll(){
		return repository.findAllAccounts();
	}
	
	public boolean insert(Account a, int id) {
		return repository.insert(a, id);
	}
	
	public boolean update(Account a, int id) {
		return repository.update(a, id);
	}
}
