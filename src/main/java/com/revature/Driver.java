package com.revature;

import java.util.Scanner;

import org.apache.log4j.Logger;

/*
 * The main class for interacting with everything 
 */
public class Driver {

	private static final Logger logger = Logger.getLogger(Driver.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		
		//first we have a menu to either log in or create an account
		//create an account will add a customer to the customer table
		//log in will match either a customer or employee in the database and will probably
		//search a database depending on what the email is.
		
		//customer menu will have create an account and select an account
		
		//employee will have that and the ability to search and view other customer accounts
	}

}
