package com.revature.services;
import java.util.List;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

public class EmployeeService {

	EmployeeDAO repository = new EmployeeDAOImpl();
	
	public List<Employee> findAll(){
		return repository.findAll();
	}
}
