package com.revature.models;

import java.util.HashMap;
import java.util.Map;

/*
 * Class representing people with at least one bank account with the bank
 */
public class Customer extends User{

	private int id;
	private String username;
	private String password;
	private Map<Integer, Account> accounts = new HashMap<>();
	private Account currAccount;
	private String fName;
	private String lName;
	
	public Customer(int id, String username, String password, String fName, String lName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
	}
	public Account getCurrAccount() {
		return currAccount;
	}
	public void setCurrAccount(String accNum) {
		this.currAccount = accounts.get(accNum);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public void addAccount(AccountType accType, int accNum) {
		Account account = new Account(accType, accNum);
		accounts.put(accNum, account);
	}
	
	public void addAccount(AccountType accType, int accNum, int balance) {
		Account account = new Account(accType, accNum, balance);
		accounts.put(accNum, account);
	}
	
	public void removeAccount(String accNum) {
		accounts.remove(accNum);
	}
	
	public Account getAccount(String accNum) {
		return accounts.get(accNum);
	}
	
	public Map<Integer, Account> getAccounts(){
		return accounts;
	}
	
	public void transfer(int amount, String accNum) {
		Account transferTo = getAccount(accNum);
		currAccount.withdraw(amount);
		transferTo.deposit(amount);
	}
}
