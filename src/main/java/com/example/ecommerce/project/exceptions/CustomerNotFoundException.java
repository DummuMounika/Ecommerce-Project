package com.example.ecommerce.project.exceptions;

public class CustomerNotFoundException extends RuntimeException {
	
	public CustomerNotFoundException(String errorMsg) {
		super(errorMsg);
	}
}
