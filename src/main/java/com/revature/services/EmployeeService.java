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
	
	//TODO:will add login service here
	public boolean login(String username, String password) {
		return true;
	}
}
