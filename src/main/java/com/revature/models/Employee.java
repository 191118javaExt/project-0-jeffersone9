package com.revature.models;

public class Employee{

	private EmployeeRoles role;
	private String fName;
	private String lName;
	private String id;
	private Account currAccount;
	
	public Employee(EmployeeRoles role, String fName, String lName, String id) {
		this.role = role;
		this.fName = fName;
		this.lName = lName;
		this.id = id;
	}

	public EmployeeRoles getRole() {
		return role;
	}

	public void setRole(EmployeeRoles role) {
		this.role = role;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Account getCurrAccount() {
		return currAccount;
	}

	public void setCurrAccount(Account currAccount) {
		this.currAccount = currAccount;
	}
	
	
}
