package com.nt.service;

import org.springframework.stereotype.Service;

import com.nt.entity.Employee;

@Service
public class EmployeeService {

	public Employee getEmployee(int id) {
		return new Employee(1,"Sundar");
	}
}
