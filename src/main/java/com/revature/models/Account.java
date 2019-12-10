package com.revature.models;

/*
 * This class holds the information for a bank account including type, number, amount of money,
 * and who the account holder is.
 */
public class Account {

	private int balance;
	private AccountType accType;
	private String accNumber;
	private String routeNumber;
	
	//A constructor for creating an account
	public Account(AccountType accType, String accNum, String routeNum) {
		super();
		this.accType = accType;
		accNumber = accNum;
		routeNumber = routeNum;
		balance = 0;
	}
	
	//A constructor including an initial balance
	public Account(AccountType accType, String accNum, String routeNum, int balance) {
		super();
		this.accType = accType;
		accNumber = accNum;
		routeNumber = routeNum;
		this.balance = balance;
	}

	//getters and setters
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}


	public AccountType getAccType() {
		return accType;
	}

	public void ChangeAccType(AccountType accType) {
		this.accType = accType;
	}
	
	
	
	public String getRouteNumber() {
		return routeNumber;
	}


	public String getAccNumber() {
		return accNumber;
	}


	public void setAccType(AccountType accType) {
		this.accType = accType;
	}

	public void Withdraw(int amount) throws IllegalArgumentException{
		if(amount < 0 || amount > balance) {
			throw new IllegalArgumentException();
		}
		balance -= amount;
	}
	
	public void Deposit(int amount) throws IllegalArgumentException{
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		balance += amount;
	}
	
}
