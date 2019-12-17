package com.revature.services;

import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerDAOImpl;
import java.util.List;
import com.revature.models.Customer;

public class CustomerService {
	CustomerDAO repository = null;
	
	public CustomerService() {
		repository = new CustomerDAOImpl();
	}
	
	public CustomerService(CustomerDAOImpl dao) {
		repository = dao;
	}
	
	public List<Customer> findAll(){
		return repository.findAllCustomers();
	}
	
	public boolean insert(Customer c) {
		return repository.insert(c);
	}
	
	public Customer findByLogin(String user, String pass) {
		return repository.findByLogin(user, pass);
	}
}
