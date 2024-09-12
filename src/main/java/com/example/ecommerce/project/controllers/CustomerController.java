package com.example.ecommerce.project.controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.project.jwt.security.JwtHelper;
import com.example.ecommerce.project.model.Customer;
import com.example.ecommerce.project.model.JwtRequest;
import com.example.ecommerce.project.model.JwtResponse;
import com.example.ecommerce.project.services.CustomerService;


@RestController
public class CustomerController {
	
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);

	
	private UserDetailsService userDetailsService;
    private AuthenticationManager manager;
    private JwtHelper helper;
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(UserDetailsService userDetailsService, AuthenticationManager manager, JwtHelper helper,
			CustomerService customerService) {
		super();
		this.userDetailsService = userDetailsService;
		this.manager = manager;
		this.helper = helper;
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
	

	//@PostMapping("/public/customers")
	@PostMapping("/customers")
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
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginConfirmation(@RequestBody Customer customer) {
		    this.doAuthenticate(customer.getCustomerEmail(), customer.getCustomerPassword());
			
	        // Load user details
	        UserDetails userDetails = userDetailsService.loadUserByUsername(customer.getCustomerEmail());
	        String token = this.helper.generateToken(userDetails);
	        Timestamp expiredTime = this.helper.getExpirationDateFromToken(token);
	
	        JwtResponse response = JwtResponse.builder()
	                .jwtToken(token)
	                .username(userDetails.getUsername())
	                .expiredTime(expiredTime)
	                .build();
	        return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/public/current-user")
	public String getLoggerInUser(Principal principal) {
		return principal.getName();
	}
	
	//JWT Authentication
	
//	 @PostMapping("/auth/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
//        
//    	 // Authenticate the user
//        this.doAuthenticate(request.getEmail(), request.getPassword());
//        // Load user details
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        String token = this.helper.generateToken(userDetails);
//        Timestamp expiredTime = this.helper.getExpirationDateFromToken(token);
//
//        JwtResponse response = JwtResponse.builder()
//                .jwtToken(token)
//                .username(userDetails.getUsername())
//                .expiredTime(expiredTime)
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
	    private void doAuthenticate(String email, String password) {

	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	            manager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }

	    

	}
	
