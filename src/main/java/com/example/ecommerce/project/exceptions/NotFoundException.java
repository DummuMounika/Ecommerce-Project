package com.example.ecommerce.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
	
	public NotFoundException(String errormessage) {
		super(errormessage);
	}

}
