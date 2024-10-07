package com.example.ecommerce.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.ecommerce.project.model.Error;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({CustomerNotFoundException.class})
	public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException exception){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
	}
	
	@ExceptionHandler({CustomerExistsException.class})
	public ResponseEntity<Object> handleCustomerAlreadyExistsException(CustomerExistsException exception){
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
	}
	
	@ExceptionHandler({NotFoundException.class})
	public ResponseEntity<Object> handleNotFoundException(NotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(exception.getMessage(),"Failed"));
	}
	
	
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(exception.getMessage(),"Failed"));
	}
	
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<Object> handleRuntimeException(RuntimeException exception){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
	}
	
	@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> credentialException(BadCredentialsException exception){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

}
