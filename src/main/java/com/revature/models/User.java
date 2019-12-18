package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * User is gonna be either a customer or employee.
 * Either way its the person interacting with the accounts.
 * I will probably make this class generic
 */
public abstract class User {

	//user is probably gonna be used for login
	private String password;
	private String email;
	private Account currAccount;
	private int id;
	private List<Account> accounts = new ArrayList<>();
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public Account getCurrAccount() {
		return currAccount;
	}
	public void setCurrAccount(Account currAccount) {
		this.currAccount = currAccount;
	}
	public void addAccount(AccountType accType, int accNum) {
		Account account = new Account(accType, accNum);
		this.accounts.add(account);
	}
	
	public void addAccount(AccountType accType, int accNum, int balance) {
		Account account = new Account(accType, accNum, balance);
		this.accounts.add(account);
	}
	
	public void removeAccount(int accNum) {
		this.accounts.remove(accNum);
	}
	
	public Account getAccount(int accNum) {
		return this.accounts.get(accNum);
	}
	
	public void transfer(double amount, int accNum) {
		Account transferTo = getAccount(accNum);
		currAccount.withdraw(amount);
		transferTo.deposit(amount);
	}
}
