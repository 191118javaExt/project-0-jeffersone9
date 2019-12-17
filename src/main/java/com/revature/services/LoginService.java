package com.revature.services;

public class LoginService {

	CustomerService cs = new CustomerService();
	EmployeeService es = new EmployeeService();
	
	//both services return null if the user is not in the database
	public boolean login(String user, String pass) {
		if(cs.findByLogin(user, pass) == null && es.findByLogin(user, pass) == null) {
			return false;
		}
		return true;
	}
}
