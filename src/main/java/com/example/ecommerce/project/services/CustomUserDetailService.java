package com.example.ecommerce.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ecommerce.project.repository.CustomerRepository;



@Service
public class CustomUserDetailService implements UserDetailsService{


	private CustomerRepository customerRepository;

	@Autowired
	public CustomUserDetailService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//load user from database
		return customerRepository.findBycustomerEmail(username)
				.orElseThrow(()-> new RuntimeException("User not found"));

	}

}
