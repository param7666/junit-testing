package com.nt.controllertest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nt.controller.AccountController;
import com.nt.entity.Account;
import com.nt.service.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

	@Mock
	private AccountServiceImpl ser;
	
	@InjectMocks
	private AccountController con;
	
	
	@BeforeAll
	public static void init() {
		System.out.println("Account COntroller Test Start");
	}
	
	@Test
	@DisplayName("Test openAccount method with Null values ")
	public void testOpenAccountWithNull() {
		Account account=new Account();
		account.setAccNo(null); account.setAdharNumber(null); account.setName(null);
		ResponseEntity<?> res=con.OpenAccount(account);
		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
		assertEquals("Invalid Details", res.getBody());
	}
	
	@Test
	@DisplayName("Test openAccount method with Wrong values ")
	public void testOpenAccountWithWrongValues() {
		Account account= new Account();
		account.setName("Parmeshwar");
		account.setMobileNumber(12345L);
		account.setAdharNumber("678910");
		ResponseEntity<?> res=con.OpenAccount(account);
		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
		assertEquals("Wrong mobile number or Adhar number", res.getBody());
	}
	
	@Test
	@DisplayName("Test openAccount method with valid details")
	public void testOpenAccountWithValidDetails() throws Exception {
		Account account=new Account();
		account.setAccNo("123432451234"); account.setAdharNumber("123432453432"); account.setName("Sakshi");
		account.setMobileNumber(1234543212L);
		 Account savedAcc = new Account();
	        savedAcc.setId(1L);
	        savedAcc.setAdharNumber(account.getAdharNumber());
	        savedAcc.setName(account.getName());
	        savedAcc.setMobileNumber(account.getMobileNumber());
	        savedAcc.setName(account.getName());
	        when(ser.createAccount(account)).thenReturn(savedAcc);
	        
		ResponseEntity<?> res=con.OpenAccount(account);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertNotNull(res);
		assertEquals(savedAcc, res.getBody());
		
	}
	
	
	@Test
	public void testOpenAccountForInternalServerError() throws Exception {
		Account account=new Account();
		account.setAccNo("123432451234"); account.setAdharNumber("123432453432"); account.setName("Sakshi");
		account.setMobileNumber(1234543212L);
		
		when(ser.createAccount(account)).thenThrow(new RuntimeException("Service Failed"));
		ResponseEntity<?> res=con.OpenAccount(account);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
        assertEquals("Service Failed", res.getBody());
	}
	
	
	@AfterAll
	public static void AfterAll() {
		System.out.println("All The test cases are done");
	}
}
