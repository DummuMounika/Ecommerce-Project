package com.example.EcommerceProject.services;

import java.util.List;

import com.example.EcommerceProject.model.Customer;


public interface CustomerService {
	
	public List<Customer> getAllCustomerDetails();
	public boolean addCustomerInfo(Customer customer);
	public String deleteCustomerInfo(int customer_Id);
	public Customer updateCustomerInfo(Customer customer, int customer_Id);
	public Customer getSingleCustomerInfo(int customer_Id);
	public Boolean isUserAuthorized(Customer customer);
	}
