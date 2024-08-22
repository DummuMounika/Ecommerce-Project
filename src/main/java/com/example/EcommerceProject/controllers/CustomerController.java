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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.EcommerceProject.model.Customer;
import com.example.EcommerceProject.services.CustomerService;


@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/public/customers")
	public ResponseEntity<List<Customer>> getAllCustomerDetails() {
		List<Customer> customers = customerService.getAllCustomerDetails();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/public/customers/{customer_Id}")
	public ResponseEntity<Customer> getSingleCustomerDetail(@PathVariable Integer customer_Id) {
		Customer customer = customerService.getSingleCustomerInfo(customer_Id);
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
	
	@DeleteMapping("/public/customers/{customer_Id}")
	public ResponseEntity<String> deleteCustomerDetail(@PathVariable Integer customer_Id) {
		try {
			String customerStatus = customerService.deleteCustomerInfo(customer_Id);
		    return new ResponseEntity<>(customerStatus, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
		
	}
	
	@PutMapping("public/customers/{customer_Id}")
	public ResponseEntity<String> updateCustomerDetail(@RequestBody Customer customer, @PathVariable Integer customer_Id) {
		try {
			Customer savedCustomer = customerService.updateCustomerInfo(customer, customer_Id);
			return new ResponseEntity<>("Customer Detail with: " + customer_Id + " updated successfully",HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
		
	}
	
	
	@PostMapping("/public/login")
	public ResponseEntity<String> loginConfirmation(@RequestBody Customer customer) {
		System.out.println("Hi");
		Boolean isAuthorized = customerService.isUserAuthorized(customer);
		if (isAuthorized) {
			return new ResponseEntity<>("Login successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Login unsuccessfully",HttpStatus.UNAUTHORIZED);
		}
		
	}
//	
//	@PutMapping("/public/resetPassword")
//	public ResponseEntity<String> resetPassword(@RequestBody Customer customer, @RequestParam Integer customer_Id){
//		
//	}
	
	

}
