package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Product;
import com.nt.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	
	@Autowired
	private ProductService service;
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable int id) {
		return service.getProduct(id);
	}
	
	@GetMapping("/categ")
	public List<String> getCategories(){
		return service.getCategories();
	}
	
	
}
