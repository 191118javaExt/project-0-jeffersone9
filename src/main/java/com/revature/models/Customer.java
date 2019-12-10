package com.revature.models;

import java.util.ArrayList;
import java.util.List;

public class Customer{

	private String username;
	private String password;
	private List<Account> accounts = new ArrayList<>();
	private Account currAccount;
	
	public Customer(String username, String password) {
		this.username = username;
		this.password = password;
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
}
