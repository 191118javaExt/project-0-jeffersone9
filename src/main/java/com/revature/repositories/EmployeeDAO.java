package com.revature.repositories;
import java.util.List;

import com.revature.models.Employee;

/*
 * The DAO methods are declared in the DAO interface and will be used in the 
 * DAO implementation class
 */
public interface EmployeeDAO {

	public List<Employee> findAll();
	public Employee findById(int id);
	public boolean update(Employee e);
	public boolean insert(Employee e);
	public Employee findByLogin(String user, String pass);
}
