package com.example.EcommerceProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcommerceProject.model.Customer;
import com.example.EcommerceProject.services.CustomerService;


@RestController
public class CustomerController {
	
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/public/customers")
	public ResponseEntity<List<Customer>> getAllCustomerDetails() {
		List<Customer> customers = customerService.getAllCustomerDetails();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/public/customers/{customerId}")
	public ResponseEntity<Customer> getSingleCustomerDetail(@PathVariable Integer customerId) {
		Customer customer = customerService.getSingleCustomerInfo(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	

	@PostMapping("/public/customers")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
		boolean isCreated = customerService.addCustomerInfo(customer);
		if (isCreated) {
			return new ResponseEntity<>("New customer added successfully",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Failed to add new customer", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/public/customers/{customerId}")
	public ResponseEntity<String> deleteCustomerDetail(@PathVariable Integer customerId) {
		String customerStatus = customerService.deleteCustomerInfo(customerId);
	    return new ResponseEntity<>(customerStatus, HttpStatus.OK);
	}
	
	@PutMapping("public/customers/{customerId}")
	public ResponseEntity<String> updateCustomerDetail(@RequestBody Customer customer, @PathVariable Integer customerId) {
		customerService.updateCustomerInfo(customer, customerId);
		return new ResponseEntity<>("Customer Detail with: " + customerId + " updated successfully",HttpStatus.OK);
		
	}
	
	
	@PostMapping("/public/login")
	public ResponseEntity<String> loginConfirmation(@RequestBody Customer customer) {
		Boolean isAuthorized = customerService.isUserAuthorized(customer);
		if (Boolean.TRUE.equals(isAuthorized)) {
			return new ResponseEntity<>("Login successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Login unsuccessfully",HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	

}
