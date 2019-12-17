package com.revature.models;

import java.util.HashMap;

public class Employee{

	private EmployeeRoles role;
	private String fName;
	private String lName;
	private int id;
	private Employee supervisor;
	private double salary;
	private String email;
	private String password;
	private Account currAccount;
	private HashMap<PhoneType, String> phoneNumbers;
	
	public Employee(EmployeeRoles role, String fName, String lName, int id, String email, String password, double salary) {
		this.role = role;
		this.fName = fName;
		this.lName = lName;
		this.id = id;
		this.email = email;
		this.password = password;
		this.salary = salary;
	}

	public EmployeeRoles getRole() {
		return role;
	}

	public void setRole(String role) {
		switch(role) {
		case "Associate":
			this.role = EmployeeRoles.Default;
			break;
		case "Manager":
			this.role = EmployeeRoles.Admin;
			break;
		default:
			//TODO: this role does not exist, may need to throw
		}
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getCurrAccount() {
		return currAccount;
	}

	public void setCurrAccount(Account currAccount) {
		this.currAccount = currAccount;
	}
	
	
}
