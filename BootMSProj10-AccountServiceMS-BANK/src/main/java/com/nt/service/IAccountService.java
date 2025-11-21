package com.nt.service;

import com.nt.entity.Account;

public interface IAccountService {

	public Account createAccount(Account a) throws Exception;
	
	public Account getAccountDetails(String accNo) throws Exception;
	
	public String updateBalance(String accNo,Double newBalance) throws Exception;
}
//AccountService
//
//createAccount(Account acc)
//
//getAccountById(Long id)
//
//updateBalance(Long id, Double newBalance)
//
//getAllAccounts()