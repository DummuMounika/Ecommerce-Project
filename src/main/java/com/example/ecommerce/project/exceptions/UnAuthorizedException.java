package com.example.ecommerce.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException{
	
	public UnAuthorizedException(String errorMsg) {
		super(errorMsg);		
	}

}
