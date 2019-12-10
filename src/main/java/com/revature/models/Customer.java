package com.revature.models;

import java.util.ArrayList;
import java.util.List;

/*
 * Class representing people with at least one bank account with the bank
 */
public class Customer{

	private String username;
	private String password;
	private List<Account> accounts = new ArrayList<>();
	private Account currAccount;
	private String fName;
	private String lName;
	
	public Customer(String username, String password, String fName, String lName) {
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
	}
	public Account getCurrAccount() {
		return currAccount;
	}
	public void setCurrAccount(Account currAccount) {
		this.currAccount = currAccount;
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
	
	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public void removeAccount(Account account) {
		accounts.remove(account);
	}
	
	public List<Account> getAccounts(){
		return accounts;
	}
}
