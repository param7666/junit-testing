package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Account;
import com.nt.repositoy.IAcountRepository;
import com.nt.utility.AccountNumberGenerator;


@Service
public class AccountServiceImpl implements IAccountService{
	

	@Autowired
	private IAcountRepository repo;
	
	
	@Override
	public Account createAccount(Account a) throws Exception {
		System.out.println("AccountServiceImpl.createAccount()");
		try {
			String accNo=String.valueOf(AccountNumberGenerator.generateUniqueAccountNumber());
			a.setAccNo(accNo);
			return repo.save(a);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public Account getAccountDetails(String accNo) throws Exception {
		System.out.println("AccountServiceImpl.getAccountDetails()");
		try {
			return repo.getAcountDetailsByAccountNumber(accNo).orElseThrow(()->new RuntimeException("Account Not Found"));
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String updateBalance(String accNo, Double newBalance) throws Exception {
		System.out.println("AccountServiceImpl.updateBalance()");
		try {
			Account a=repo.getAcountDetailsByAccountNumber(accNo).orElseThrow(()->new IllegalArgumentException("Acount not found"));
			a.setBalance(newBalance);
			repo.save(a);
			return "Balance updated with Account number "+accNo+" is "+newBalance;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

}
