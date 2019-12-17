package com.revature;

import java.util.Scanner;

import com.revature.models.Bank;
import com.revature.models.Customer;
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
		case "1": createAccount();
			break;
		case "2": //TODO: menu for logging in
			break;
		case "q":
			System.exit(0);
			break;
		default:
			System.out.println("Not a valid input");
			openMenu();
		}
	}
	
	public static void createAccount() {
		System.out.println("Enter the email of the new account:");
		String username = input.nextLine();
		System.out.println("Enter your password for the account:");
		String password = input.nextLine();
		System.out.println("Enter your first name");
		String first = input.nextLine();
		System.out.println("Enter your last name");
		String last = input.nextLine();
		Customer c =  new Customer(bank.getCustomerSize() + 1, username, password, first, last);
		cs.insert(c);
		bank.addCustomer(bank.getCustomerSize(), c);
		System.out.println("Account has been created");
	}

}
