package com.example.EcommerceProject.exceptions;

public class CustomerNotFoundException extends RuntimeException {
	
	public CustomerNotFoundException(String errorMsg) {
		super(errorMsg);
	}
}
