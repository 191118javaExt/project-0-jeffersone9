package com.revature.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * A bank object that holds all of the customers and employees.
 * There should only be one bank, so we use Singleton
 */
public class Bank {

	private Map<Integer, Customer> customers = new HashMap<>();
	private Map<Integer, Employee> employees = new HashMap<>();
	private List<Account> accounts = new ArrayList();
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
	
	public void addCustomer(int id, Customer customer) {
		customers.put(id, customer);
	}
	
	public void addEmployee( int id, Employee employee) {
		employees.put(id, employee);
	}
	
	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public void addCustomer(List<Customer> c) {
		for(Customer customer : c) {
			customers.put(customer.getId(), customer);
		}
	}
	
	public void addEmployee(List<Employee> e) {
		for(Employee employee: e) {
			employees.put(employee.getId(), employee);
		}
	}
	
	public void addAccount(List<Account> a) {
		for(Account account : a) {
			accounts.add(account);
		}
	}
	
	public int getEmployeeSize() {
		return employees.size();
	}
	
	public int getCustomerSize() {
		return customers.size();
	}
	
	public int getAccountSize() {
		return accounts.size();
	}
	
	public Employee getEmployee(int id) {
		return employees.get(id);
	}
	
	public Customer getCustomer(int id) {
		return customers.get(id);
	}
	
	public Account getAccount(int id) {
		return accounts.get(id);
	}
}
