package com.example.ecommerce.project.exceptions;

public class CustomerExistsException extends RuntimeException {
	
	public CustomerExistsException(String errorMsg) {
		super(errorMsg);
	}
}
