package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	private static Logger logger = Logger.getLogger(AccountDAOImpl.class);
	@Override
	public List<Account> findAllAccounts() {
		List<Account> accounts = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank.accounts";
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("acc_id");
				double balance =  rs.getDouble("balance");
				String type = rs.getString("acc_type");
				String status = rs.getString("status");
				
				Account a = new Account(id);
				a.changeAccType(type);
				a.setStatus(status);
				a.setBalance(balance);
				accounts.add(a);
			}	
		}catch(SQLException e) {
			logger.warn("Could not retrieve all of the accounts", e);
		}
		return accounts;
	}

	@Override
	public Account findAccountById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> getAccountsByCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
