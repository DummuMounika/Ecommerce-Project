package com.example.EcommerceProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException{
	
	public UnAuthorizedException(String ErrorMsg) {
		super(ErrorMsg);		
	}

}
