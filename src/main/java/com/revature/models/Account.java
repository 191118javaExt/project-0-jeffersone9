package com.revature.models;

/*
 * This class holds the information for a bank account including type, number, amount of money,
 * and who the account holder is.
 */
public class Account {

	private double balance;
	private AccountType accType;
	private int accNumber;
	private final String routeNumber = "11001100";
	private AccountStatus status;
	
	//most basic account constructor
	public Account(int accNum) {
		super();
		this.accNumber = accNum;
		this.balance = 0;
		this.status = AccountStatus.Open;
		this.accType = AccountType.Checking;
	}
	
	//A constructor for creating an account with account type
	public Account(AccountType accType, int accNum) {
		super();
		this.accType = accType;
		this.accNumber = accNum;
		this.balance = 0;
		this.status = AccountStatus.Open;
	}
	
	//A constructor including an initial balance
	public Account(AccountType accType, int accNum, double balance) {
		super();
		this.accType = accType;
		this.accNumber = accNum;
		this.balance = balance;
		this.status = AccountStatus.Open;
	}

	//getters and setters
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	public AccountType getAccType() {
		return accType;
	}
	
	public String getAccTypeString() {
		switch(accType) {
		case Checking:
			return "checking";
		case Savings:
			return "savings";
		case Credit:
			return "credit";
		default:
			return null;
			//invalid arg, but shouldn't happen
		}
	}

	public void changeAccType(AccountType accType) {
		this.accType = accType;
	}
	
	//db has this value as a string, so I'll convert it
	public void changeAccType(String type) {
		switch(type) {
		case "checking":
			this.changeAccType(AccountType.Checking);
			break;
		case "savings":
			this.changeAccType(AccountType.Savings);
			break;
		case "credit":
			this.changeAccType(AccountType.Credit);
			break;
		default: 
			//TODO:throw invalid arg exception
			break;
		}
	}
	
	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	
	public String getStatusString() {
		switch(this.status) {
		case Open:
			return "open";
		case Cancelled:
			return "cancelled";
		case Approved:
			return "approved";
		case Denied:
			return "denied";
		default:
			return null;
		}
	}
	
	//db will return this value as a string as well
	public void setStatus(String status) {
		switch(status) {
		case "open":
			this.setStatus(AccountStatus.Open);
			break;
		case "cancelled":
			this.setStatus(AccountStatus.Cancelled);
			break;
		case "approved":
			this.setStatus(AccountStatus.Approved);
			break;
		case "denied":
			this.setStatus(AccountStatus.Denied);
			break;
		default:
			//TODO:throw invalid arg exception
			break;
		}
	}

	public String getRouteNumber() {
		return routeNumber;
	}


	public int getAccNumber() {
		return accNumber;
	}


	public void setAccType(AccountType accType) {
		this.accType = accType;
	}

	public void withdraw(double amount) throws IllegalArgumentException{
		if(amount < 0 || amount > balance) {
			throw new IllegalArgumentException();
		}
		balance -= amount;
	}
	
	public void deposit(double amount) throws IllegalArgumentException{
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		balance += amount;
	}
	
}
