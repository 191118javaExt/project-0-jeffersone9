package com.revature;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.models.Bank;
import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.services.*;
import org.apache.log4j.Logger;

/*
 * The main class for interacting with everything 
 */
public class Driver {

	private static final Logger logger = Logger.getLogger(Driver.class);
	static EmployeeService es = new EmployeeService();
	static CustomerService cs = new CustomerService();
	static AccountService as = new AccountService();
	static LoginService ls = new LoginService();
	private static final Bank bank = Bank.getInstance();
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		//start the program by loading all the data from the database
		bank.addEmployee(es.findAll());
		bank.addCustomer(cs.findAll());
		//the menu should be manually exited
		while(true) {
			openMenu();
		}
		
		//first we have a menu to either log in or create an account
		//create an account will add a customer to the customer table
		//log in will match either a customer or employee in the database and will probably
		//search a database depending on what the email is.
		
		//customer menu will have create an account and select an account
		
		//employee will have that and the ability to search and view other customer accounts
	}
	
	//TODO: throw illegal argument exception instead maybe
	public static void openMenu() {
		System.out.println("To create an account press 1:");
		System.out.println("To login to an existing account press 2: ");
		System.out.println("To exit press q");
		String key = input.nextLine();
		switch(key) {
		case "1": createUserAccount();
			break;
		case "2": login();
			break;
		case "q":
			System.exit(0);
			break;
		default:
			System.out.println("Not a valid input");
			openMenu();
		}
	}
	
	public static void createUserAccount() {
		System.out.println("Enter the email of the new account:");
		String username = input.nextLine();
		System.out.println("Enter your password for the account:");
		String password = input.nextLine();
		System.out.println("Enter your first name");
		String first = input.nextLine();
		System.out.println("Enter your last name");
		String last = input.nextLine();
		Customer c =  new Customer(bank.getCustomerSize() + bank.getEmployeeSize() + 1, username, password, first, last);
		cs.insert(c);
		bank.addCustomer(bank.getCustomerSize(), c);
		logger.info("Account created");
	}
	
	public static void login() {
		System.out.println("Enter email:");
		String username = input.nextLine();
		System.out.println("Enter your password");
		String password = input.nextLine();
		//if the user is in the database
		if(ls.login(username, password)) {
			//if login is an employee
			if(es.login(username, password) != null) {
				User user = es.login(username, password);
				logger.info("Employee has logged in");
			}
			else {
				User user = cs.findByLogin(username, password);
				logger.info("Customer has logged in");
			}
		}
		//once you have successfully logged in, you can change your account
	}
	
	public static void accountMenu(User user) {
		while(true) {
			//this also needs to be manually exited
			System.out.println("Welcome: ");
			System.out.println("1. Create an account");
			System.out.println("2. View account");
			System.out.println("press q to quit");
			String key = input.nextLine();
			switch(key) {
			case "1":
				determineBankAccount(user);
				break;
			case "2":
				viewBankAccounts(user);
				break;
			case "q":
				System.exit(0);
			default:
				System.out.println("Not a valid option");
				accountMenu(user);
			}
		}
	}
	
	//This is just to help us decide what type of account we will be creating
	public static void determineBankAccount(User user) {
		
		System.out.println("What type of account will you be making?");
		System.out.println("1. Checking");
		System.out.println("2. Savings");
		System.out.println("3. Credit");
		String key = input.nextLine();
		switch(key) {
		case "1": createBankAccount(AccountType.Checking, user);
			break;
		case "2": createBankAccount(AccountType.Savings, user);
			break;
		case "3": createBankAccount(AccountType.Credit, user);
			break;
		default:
			System.out.println("Not a valid option");
			determineBankAccount(user);
		}
	}
	
	public static void createBankAccount(AccountType type, User user) {
		//status defaults to open until an employee changes it
		AccountStatus status = AccountStatus.Open;
		Account a = new Account(bank.getAccountSize() + 1);
		a.setAccType(type);
		a.setStatus(AccountStatus.Open);
		System.out.println("How much money will you be starting with?");
		Double balance = input.nextDouble();
		a.setBalance(balance);
		if(as.insert(a, user.getId())) {
			bank.addAccount(a);
			user.addAccount(type, a.getAccNumber());
			logger.info("Account has been added");
		}
		else {
			logger.warn("Account was not added");
		}
	}
	public static void viewBankAccounts(User user) {
		List<Account> accounts = new ArrayList<>();
		Account currAccount = null;
		if(user.getAccounts().size() == 0)
		{
			logger.warn("You do not have any accounts under this user");
		}
		else if (user.getAccounts().size() == 1){
			accounts = user.getAccounts();
			currAccount = accounts.get(0);
		}
		else {
			System.out.println("Which account do you want to view?");
			accounts = user.getAccounts();
			for(int i = 0; i < accounts.size(); i++) {
				System.out.println(String.format("%d: Account number %d", i + 1, accounts.get(i).getAccNumber()));
			}
			int index = input.nextInt();
			currAccount = accounts.get(index - 1);
		}
		user.setCurrAccount(currAccount);
		checkAccount(user, currAccount);
	}
	
	public static void checkAccount(User user, Account account) {
		switch(account.getStatus()) {
		case Open: logger.warn("Account was opened, but has not been approved by the bank");
			break;
		case Denied: logger.warn("Your account was denied creation and will be deleted soon");
			break;
		case Cancelled: logger.warn("This account was cancelled by the bank and will be deleted shortly");
			break;
			//customers can only check approved accounts
		case Approved: updateAccount(user, account); 
			break;
		}
	}
	
	public static void updateAccount(User user, Account account) {
		//manually exits
		while(true) {
			
			System.out.println("Balance: %.2f");
			System.out.println("1. Withdraw");
			System.out.println("2. Deposit");
			System.out.println("3. Transfer");
			System.out.println("press q to quit");
			String key = input.nextLine();
			switch(key) {
			case "1": System.out.println("How much money are you withdrawing?");
				Double withdraw = input.nextDouble();
				System.out.println("Is %.2f correct? y or n");
				if(input.nextLine().equals("y")) {
					account.withdraw(withdraw);
					as.update(account, user.getId());
				}
				else {
					continue;
				}
				break;
			case "2": System.out.println("How much money are you depositing?");
				Double deposit = input.nextDouble();
				System.out.println("Is %.2f correct? y or n");
				if(input.nextLine().equals("y")) {
					account.deposit(deposit);
					as.update(account, user.getId());
				}
				else {
					continue;
				}
				break;
			case "3": System.out.println("How much money will you be transferring?");
				Double transfer = input.nextDouble();
				System.out.println("Is %.2f right? y or n");
				if(input.nextLine().equals("y")) {
					System.out.println("Enter the account you wish to transfer money to");
					int transferId = input.nextInt();
					user.transfer(transfer, transferId);
					as.update(account, user.getId());
					as.update(user.getAccount(transferId), user.getId());
				}
				break;
			case "q":
				System.exit(0);
			}
		}
	}

}
