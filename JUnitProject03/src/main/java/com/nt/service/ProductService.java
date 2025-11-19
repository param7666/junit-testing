package com.nt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nt.entity.Product;

@Service
public class ProductService {

	
	public Product getProduct(int id) {
		if(id<=0) {
			throw new IllegalArgumentException("Invalid Id");
		}
		return new Product(id,"laptop",50000);
	}
	
	public List<String> getCategories() {
		return List.of("Electronics", "Home", "Fashion");
	}
	
	public double applyDiscount(double price, double discount) {
		if(price<0) {
			throw new IllegalArgumentException("Price must be > 0");
		}
		if(discount<0 || discount > 50) {
			throw new IllegalArgumentException("Discount must be between 0 to 50");
		}
		 return price - (price * discount / 100);
	}
}

