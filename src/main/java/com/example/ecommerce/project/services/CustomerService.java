package com.example.ecommerce.project.services;

import java.util.List;

import com.example.ecommerce.project.model.Customer;


public interface CustomerService {
	
	public List<Customer> getAllCustomerDetails();
	public boolean addCustomerInfo(Customer customer);
	public String deleteCustomerInfo(int customerId);
	public Customer updateCustomerInfo(Customer customer, int customerId);
	public Customer getSingleCustomerInfo(int customerId);
	public Boolean isUserAuthorized(Customer customer);
	}
