package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO {

	private static Logger logger = Logger.getLogger(CustomerDAOImpl.class);
	
	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank.customer";
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("customer_id");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				Customer c = new Customer(id, first, last, email, password);
				customers.add(c);
			}
		}catch(SQLException e) {
			logger.warn("Unable to get all customers from the database", e);
		}
		return customers;
	}

	@Override
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Customer c) {
		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO bank.customer ";
			sql += String.format("VALUES('%d', '%s', '%s', '%s', '%s')", c.getId(), c.getfName(), c.getlName(),
					c.getUsername(), c.getPassword());
			Statement stmnt = con.createStatement();
			stmnt.executeUpdate(sql);
		}catch(SQLException e) {
			logger.warn("Unable to add customer to DB", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Customer c) {
		// TODO Auto-generated method stub
		return false;
	}

}
