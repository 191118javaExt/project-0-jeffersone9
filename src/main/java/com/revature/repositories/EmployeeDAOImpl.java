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
			String sql = "SELECT * FROM employee";
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
				double salary = rs.getDouble("salary");
				int sup_id = rs.getInt("supervisor");
				//now that we have all the data, we can create an employee object and add it to
				//the list
				Employee e = new Employee(EmployeeRoles.Default, first, last, id, email, salary);
				employees.add(e);
				//add the superisor id to supervisor list
				supervisors.add(sup_id);
				
			}
			//close the result set when you're done with it
			rs.close();
		}catch(SQLException e) {
			logger.warn("Unable to get all employees from database", e);
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
		// TODO Auto-generated method stub
		return null;
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
}
