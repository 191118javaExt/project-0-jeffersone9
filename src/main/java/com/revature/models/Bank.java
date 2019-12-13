package com.revature.models;

import java.util.HashMap;
import java.util.Map;

/*
 * A bank object that holds all of the customers and employees.
 * There should only be one bank, so we use Singleton
 */
public class Bank {

	public Map<String, Customer> customers = new HashMap<>();
	public Map<Integer, Employee> employees = new HashMap<>();
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
	
	public void addCustomer(String username, String password, String fName, String lName) {
		Customer customer = new Customer(username, password, fName, lName);
		customers.put(username, customer);
	}
	
	public void addEmployee(EmployeeRoles role, String fName, String lName, int id, String email, double salary) {
		Employee employee = new Employee(role, fName, lName, id, email, salary);
		employees.put(id, employee);
	}
}
