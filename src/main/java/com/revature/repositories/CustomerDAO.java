package com.revature.repositories;
import java.util.List;
import com.revature.models.Customer;

public interface CustomerDAO {
	public List<Customer> findAllCustomers();
	public Customer findById(int id);
	public Customer findByLogin(String user, String pass);
	public boolean insert(Customer c);
	public boolean update(Customer c);
}
