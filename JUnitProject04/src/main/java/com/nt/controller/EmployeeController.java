package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Employee;
import com.nt.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@GetMapping("/emp/{id}")
	public Employee getEmp(@PathVariable int id) {
		if(id<=0) throw new IllegalArgumentException("Id must be > 0");
		if(id>=20) throw new IllegalArgumentException("Id must be <= 20");
		try {
			return service.getEmployee(id);
		} catch(Exception e) {
			throw new RuntimeException("Internal Server Error");
		}
	}
}
