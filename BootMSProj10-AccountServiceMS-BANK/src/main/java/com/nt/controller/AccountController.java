package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Account;
import com.nt.service.IAccountService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/account")
@RefreshScope

public class AccountController {

	@Autowired
	private IAccountService service;
	
	//@CircuitBreaker(name="BootMSProj10-AccountServiceMS-BANK", fallbackMethod = "openAccountFallback")
	@PostMapping("/create")
	public ResponseEntity<?> OpenAccount(@RequestBody Account account) {
		System.out.println("AccountController.OpenAccount()");
		if(account.getAdharNumber()==null || account.getName()==null || account.getMobileNumber()==null) {
			return new ResponseEntity<String>("Invalid Details",HttpStatus.BAD_REQUEST);
		}
		
		String mobile=String.valueOf(account.getMobileNumber());
		if(account.getAdharNumber().length()!=12 || mobile.length()!=10) {
			return new ResponseEntity<String>("Wrong mobile number or Adhar number",HttpStatus.BAD_REQUEST);
		}
		
		try {
			Account created=service.createAccount(account);
			return new ResponseEntity<Account>(created,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/details/{accNo}")
	public ResponseEntity<?> checkAccountDetails(@PathVariable("accNo")String accNo){
		
		if(accNo.length()!=12) {
			return new ResponseEntity<String>("Wrong Account Number",HttpStatus.BAD_REQUEST);
		}
		
		try {
			Account account=service.getAccountDetails(accNo);
			return new ResponseEntity<Account>(account,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//@CircuitBreaker(name="BootMSProj10-AccountServiceMS-BANK", fallbackMethod = "updateBalanceFallback")
	@GetMapping("/update/{accNo}/{balance}")
	public ResponseEntity<?> updateBalance(@PathVariable("accNo")String accNo,@PathVariable("balance")Double balance){
		String adhar=String.valueOf(accNo);
		if(adhar.length()!=12) {
			return new ResponseEntity<String>("Wrong Account Number",HttpStatus.BAD_REQUEST);
		}
		
		if(balance<0) {
			return new ResponseEntity<String>("Amount Should not be less than 0",HttpStatus.BAD_REQUEST);
		}
		
		try {
			String result=service.updateBalance(accNo, balance);
			return new ResponseEntity<String>(result,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	
//	public ResponseEntity<?> openAccountFallback(@RequestBody Account account, Exception e) {
//	    return new ResponseEntity<>("Account service is temporarily unavailable. Please try again later.", 
//	                                HttpStatus.SERVICE_UNAVAILABLE);
//	}
//	
//	public ResponseEntity<?> updateBalanceFallback(@PathVariable("accNo")String accNo,@PathVariable("balance")Double balance,  Exception e) {
//	    return new ResponseEntity<>("Account service is temporarily unavailable. Please try again later.", 
//	                                HttpStatus.SERVICE_UNAVAILABLE);
//	}
//	
	
}
