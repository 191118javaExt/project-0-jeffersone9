package com.revature.models;

import java.util.ArrayList;
import java.util.List;

/*
 * A bank object that holds all of the customers and employees.
 * There should only be one bank, so we use Singleton
 */
public class Bank {

	public List<Customer> customers = new ArrayList<>();
	public List<Employee> employees = new ArrayList<>();
	private static Bank bank = null;
	
	private Bank() {
		super();
	}
	
	public static Bank getInstance() {
		if(bank == null) {
			//lazy initialization, may add synchronized key word later
			bank = new Bank();
			return bank;
		}
		return bank;
	}
}
