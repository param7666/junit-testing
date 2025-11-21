package com.nt;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import com.nt.controller.EmployeeController;
import com.nt.entity.Employee;
import com.nt.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	@Mock
	private EmployeeService service;
	
	@InjectMocks
	private EmployeeController controller;
	
	@BeforeAll
	@DisplayName("Before All")
	public static void init() {
		System.out.println("EmployeeControllerTest.init()");
	}
	
	@Test
	@DisplayName("Method to Test Get Employee")
	public void testGetValidEmployee() {
		when(service.getEmployee(5)).thenReturn(new Employee(5,"Sundar"));
		
		Employee response=controller.getEmp(5);
		
		assertNotNull(response); // resopnse not null
		assertEquals(5, response.getId());
		assertEquals("Sundar", response.getName());
		
		// verify service call count
		verify(service, times(1)).getEmployee(5); 
	}
	
	@Test
	@DisplayName("Method to test Get Employee with valid details")
	public void testGetEmployeevalidData() {
		when(service.getEmployee(15)).thenReturn(new Employee(15,"Param"));
		Employee res=controller.getEmp(15);
		assertEquals(15, res.getId());
		assertNotNull(res);
		assertEquals("Param", res.getName());
	}
	
	@Test
	@DisplayName("Method to test GetEmployee with invalid details")
	public void testGetEmployeeInvalidId() {
		assertThrows(IllegalArgumentException.class, ()->controller.getEmp(-5));
	}
	
	
	@Test
	@DisplayName("Method to test GetEmployee with GreterValue Id details")
	public void testGetEmployeeGreterValue() {
		assertThrows(IllegalArgumentException.class, ()->controller.getEmp(35));
	}
}
