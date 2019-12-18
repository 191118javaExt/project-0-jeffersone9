package com.revature.repositories;
import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {

	public List<Account> findAllAccounts();
	public Account findAccountById(int id);
	public boolean update(Account a, int id);
	public boolean insert(Account a, int id);
	public List<Account> getAccountsByCustomer(int id);
}
