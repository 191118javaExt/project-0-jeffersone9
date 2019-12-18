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
	//the account map will have the user id as the key and a list of accounts as a value
	//since each id can have multiple accounts
	private Map<Integer, List<Account>> accounts = new HashMap<>();
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
	
	public void addAccount(Account account, int id) {
		List<Account> a = accounts.get(id);
		a.add(account);
		accounts.put(id, a);
	}
	
	public void addAccount(Map<Integer, List<Account>> accountMap) {
		this.accounts = accountMap;
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
	
	public void addAccount(List<Account> a, int id) {
		List<Account> currentList = accounts.get(id);
		for(Account account : a) {
			currentList.add(account);
		}
		accounts.put(id, currentList);
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
	
	public List<Account> getAccount(int id) {
		return accounts.get(id);
	}
	
	public Map<Integer, Employee> getEmployees(){
		return this.employees;
	}
	
	public Map<Integer, Customer> getCustomers(){
		return this.customers;
	}
	
	public Map<Integer, List<Account>> getPendingAccounts(){
		Map<Integer, List<Account>> pending = new HashMap<>();
		for(int id = 1; id <= bank.getCustomerSize() + bank.getEmployeeSize(); id++)
		{
			List<Account> pendingList = new ArrayList<>();
			if(accounts.containsKey(id)) {
				
				for(int i = 0; i < accounts.get(id).size(); i++) {
					if(accounts.get(id).get(i).getStatus() == AccountStatus.Open) {
						pendingList.add(accounts.get(id).get(i));
					}
					pending.put(id, pendingList);
			}
			
		}
		}
		return pending;
	}
	
	public Map<Integer, List<Account>> getApprovedAccounts(){
		Map<Integer, List<Account>> approved = new HashMap<>();
		for(int id = 1; id <= accounts.size(); id++) {
			List<Account> approvedList = new ArrayList<>();
			if(accounts.containsKey(id)) {
				
				for(int i = 0; i < bank.getEmployeeSize() + bank.getCustomerSize(); i++) {
					if(accounts.get(id).get(i).getStatus() == AccountStatus.Approved) {
						approvedList.add(accounts.get(id).get(i));
					}
					approved.put(id, approvedList);
				}
			}
		}
		return approved;
	}
	
	
}
