package com.revature.models;

import java.util.List;

/*
 * User is gonna be either a customer or employee.
 * Either way its the person interacting with the accounts.
 * I will probably make this class generic
 */
public abstract class User {

	//user is probably gonna be used for login
	private String password;
	private String email;
	private List<Account> accounts;
}
