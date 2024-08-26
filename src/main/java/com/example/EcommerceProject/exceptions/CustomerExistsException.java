package com.example.EcommerceProject.exceptions;

public class CustomerExistsException extends RuntimeException {
	
	public CustomerExistsException(String errorMsg) {
		super(errorMsg);
	}
}
