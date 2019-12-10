package com.revature.models;

/*
 * This class holds the information for a bank account including type, number, amount of money,
 * and who the account holder is.
 */
public class Account {

	private int balance;
	private Person holder;
	private AccountType accType;
	
	//A constructor for creating an account
	public Account(Person person, AccountType accType, String accNumber) {
		super();
		this.holder = person;
		this.accType = accType;
		final String account = accNumber;
		balance = 0;
	}
	
	//A constructor including an initial balance
	public Account(Person person, AccountType accType, String accNumber, int balance) {
		super();
		this.holder = person;
		this.accType = accType;
		final String account = accNumber;
		this.balance = balance;
	}

	//getters and setters
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Person getHolder() {
		return holder;
	}


	public AccountType getAccType() {
		return accType;
	}

	public void ChangeAccType(AccountType accType) {
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
