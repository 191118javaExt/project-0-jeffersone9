package com.revature.repositories;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.EmployeeRoles;
import com.revature.util.*;

/*
 * The implementation class uses the connection class to grab data from the database 
 * and insert them into the objects
 */
public class EmployeeDAOImpl implements EmployeeDAO{

	//create a logger for exceptions and errors
	private static Logger logger = Logger.getLogger(EmployeeDAOImpl.class);
	@Override
	public List<Employee> findAll() {
		//make list objects to put the Employee and their supervisors' data in
		List<Employee> employees = new ArrayList<>();
		List<Integer> supervisors = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()){
			//create a sql select statement to get the data from employee table
			String sql = "SELECT * FROM bank.employee";
			//Statement is a wrapper class for the String we created for the sql statement
			Statement stmnt = con.createStatement();
			//result set is where we will store the data we received from the sql db
			ResultSet rs = stmnt.executeQuery(sql);
			/*
			 * now that we have all the queried data in the result set, we will use a while
			 * loop to go through all the results and use them to create new employee objects
			 */
			while(rs.next()) {
				int id = rs.getInt("emp_id");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				double salary = rs.getDouble("salary");
				int sup_id = rs.getInt("supervisor");
				//now that we have all the data, we can create an employee object and add it to
				//the list
				Employee e = new Employee(EmployeeRoles.Default, first, last, id, email, password, salary);
				employees.add(e);
				//add the superisor id to supervisor list
				supervisors.add(sup_id);
				
			}
			//close the result set when you're done with it
			rs.close();
		}catch(SQLException e) {
			logger.warn("Unable to get all employees from database", e);
		}catch(NullPointerException e) {
			logger.warn("Connection to db not created", e);
		}
		//now we will go through the supervisor ids we got and use them to set the supervisors
		//of the employees
		for(int i = 0; i < employees.size(); i++) {
			int sup_id = supervisors.get(i);
			//now that we have a supervisor id, lets see if any employees match it
			for(Employee e : employees) {
				if(e.getId() == sup_id) {
					e.setSupervisor(e);
				}
			}
		}
		return employees;
	}

	@Override
	public Employee findById(int id) {
		Employee e = null;
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank.employee WHERE emp_id = " + id;
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			while(rs.next()) {
				
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				double salary = rs.getDouble("salary");
				int sup_id = rs.getInt("supervisor");
				e = new Employee(EmployeeRoles.Default, first, last, id, email, password, salary);
			}
			//TODO:need to set the supervisor id
			rs.close();
		}catch(SQLException ex) {
			logger.warn("Unable to get employee from database", ex);
		}
		return e;
	}

	@Override
	public boolean update(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee findByLogin(String user, String pass) {
		Employee emp = null;
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank.employee where ";
			sql += String.format("email = '%s' AND password = '%s'", user, pass);
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			while(rs.next()) {
				
				int id = rs.getInt("emp_id");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				String job = rs.getString("job");
				double salary = rs.getDouble("salary");
				emp = new Employee(EmployeeRoles.Default, first, last, id, user, pass, salary);
			}
		}catch(SQLException e) {
			logger.warn("User or password does not exist", e);
		}
		return emp;
	}
}
