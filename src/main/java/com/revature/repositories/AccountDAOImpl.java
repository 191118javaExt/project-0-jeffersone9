package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	private static Logger logger = Logger.getLogger(AccountDAOImpl.class);
	@Override
	public Map<Integer, List<Account>> findAllAccounts() {
		Map<Integer, List<Account>> accounts = new HashMap<>();
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank.accounts";
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("acc_id");
				int holderId = rs.getInt("holder_id");
				double balance =  rs.getDouble("balance");
				String type = rs.getString("acc_type");
				String status = rs.getString("status");
				Account a = new Account(id);
				a.changeAccType(type);
				a.setStatus(status);
				a.setBalance(balance);
				if(accounts.containsKey(holderId)) {
					accounts.get(holderId).add(a);
				}
				else {
					accounts.put(holderId, new ArrayList<>());
					accounts.get(holderId).add(a);
				}
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
	public boolean update(Account a, int id) {
		//the only things that should be updated are balance, type, and status
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "Update bank.accounts ";
			sql += String.format("SET balance = %.2f, acc_type = '%s', status = '%s' ",
					a.getBalance(), a.getAccTypeString(), a.getStatusString());
			sql += String.format("Where acc_id = %d", id);
			Statement stmnt = con.createStatement();
			stmnt.executeUpdate(sql);
			return true;
		}catch(SQLException e) {
			logger.warn("Unable to update account information", e);
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Account a, int id) {
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank.accounts VALUES";
			sql += String.format("(%d, %d, %.2f, '%s', '%s')", a.getAccNumber(), id, 
					a.getBalance(), a.getAccTypeString(), a.getStatusString());
			Statement stmnt = con.createStatement();
			stmnt.executeUpdate(sql);
			return true;
		}catch(SQLException e) {
			logger.warn("Unable to create account", e);
		}
		return false;
	}

	@Override
	public List<Account> getAccountsByCustomer(int id) {
		List<Account> accounts = null;
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank.accounts ";
			sql += String.format("WHERE holder_id = '%d'", id);
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			while(rs.next()) {
				int accNum = rs.getInt("acc_id");
				double balance = rs.getDouble("balance");
				String accType = rs.getString("acc_type");
				String status = rs.getString("status");
				Account a = new Account(accNum);
				a.setStatus(status);
				a.changeAccType(accType);
				a.setBalance(balance);
				accounts.add(a);
			}
		}catch(SQLException e) {
			logger.warn("Unable to get account from user", e);
		}
		return accounts;
	}

}
