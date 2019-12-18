package com.revature.services;
import java.util.List;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

//The service layer should not have any input/output, just the logic
public class EmployeeService {

	EmployeeDAO repository = null;
	
	public EmployeeService() {
		repository = new EmployeeDAOImpl();
	}
	
	//for mock setup
	public EmployeeService(EmployeeDAO dao) {
		repository = dao;
	}
	
	public List<Employee> findAll(){
		return repository.findAll();
	}
	
	public Employee findById(int id) {
		return repository.findById(id);
	}
	public Employee findByLogin(String user, String pass) {
		return repository.findByLogin(user, pass);
	}
	
	//TODO:will add login service here
	public Employee login(String username, String password) {
		return repository.findByLogin(username, password);
	}
}
