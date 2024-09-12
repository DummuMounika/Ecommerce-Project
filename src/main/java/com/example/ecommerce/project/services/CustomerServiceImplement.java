package com.example.ecommerce.project.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.project.exceptions.CustomerNotFoundException;
import com.example.ecommerce.project.exceptions.NotFoundException;
import com.example.ecommerce.project.model.Customer;
import com.example.ecommerce.project.model.entity.CustomerEntity;
import com.example.ecommerce.project.repository.CustomerRepository;

@Service
public class CustomerServiceImplement implements CustomerService {

	
	private CustomerRepository customerRepository;
	//private PasswordEncoder passwordEncoder;
	
	Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	public CustomerServiceImplement(CustomerRepository customerRepository) {
			//PasswordEncoder passwordEncoder) {
		super();
		//this.passwordEncoder = passwordEncoder;
		this.customerRepository = customerRepository;
	}

	//convertMethods
	private Customer convertCustomerEntityToCustomer(CustomerEntity customerEntity) {
		return new Customer(customerEntity.getCustomerId(),customerEntity.getCustomerFirstName(),
				customerEntity.getCustomerLastName(),customerEntity.getCustomerEmail(),
				//customerEntity.getCustomer_password()
				customerEntity.getCustomerAddress(),customerEntity.getCustomerPhoneNumber(),customerEntity.isCustomerEmailVerification(),
				customerEntity.getCustomerCreatedTime(),customerEntity.getCustomerUpdatedTime());
	}

	private List<Customer> convertCustomerEntityListToCustomerList(List<CustomerEntity> customerEntityList){
		List<Customer> customerList = new ArrayList<>();
		for(CustomerEntity customerEntity: customerEntityList) {
			Customer customer = convertCustomerEntityToCustomer(customerEntity);
			customerList.add(customer);
		}
		return customerList;
	}

	private CustomerEntity convertCustomerToCustomerEntity(Customer customer) {
		return new CustomerEntity(customer.getCustomerFirstName(),customer.getCustomerLastName(),customer.getCustomerEmail(),
				//customer.getCustomer_password(),
				customer.getCustomerAddress(),customer.getCustomerPhoneNumber(),customer.isCustomerEmailVerification());
	}


	//CRUD  Methods
	@Override
	public List<Customer> getAllCustomerDetails() {
		List<CustomerEntity> customerEntityList = customerRepository.findAll();
		return convertCustomerEntityListToCustomerList(customerEntityList);
	}

	@Override
	public boolean addCustomerInfo(Customer customer) {
	 try {
	        // Convert Customer to CustomerEntity
	        CustomerEntity customerEntity = convertCustomerToCustomerEntity(customer);
	        
	        // Hash the password
	        customerEntity.setCustomerPassword(BCrypt.hashpw(customerEntity.getCustomerPassword(), BCrypt.gensalt()));
	        
	        // Set created and updated timestamps
	        Timestamp currentTimestamp = customerRepository.findCurrentTimeStamp();
	        customerEntity.setCustomerCreatedTime(currentTimestamp);
	        customerEntity.setCustomerUpdatedTime(currentTimestamp);
	        
	        // Save the entity
	        customerRepository.save(customerEntity);
	        return true;
	    } catch (Exception e) {
	        // Log the exception
	        logger.severe("Failed to add customer info: " + e.getMessage());
	        return false;
	    }
	}

	@Override
	public String deleteCustomerInfo(int customerId) {
		CustomerEntity removeCustomerEntity = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("customer Id not found"));
		customerRepository.delete(removeCustomerEntity);
		return "The " + customerId + " deleted successfully" ;
	}

	@Override
	public Customer updateCustomerInfo(Customer customer, int customerId) {
	    CustomerEntity existingCustomerEntity = customerRepository.findById(customerId)
	            .orElseThrow(() -> new CustomerNotFoundException("Customer not found with the given ID."));
	    
	    // Update customer details
	    existingCustomerEntity.setCustomerFirstName(customer.getCustomerFirstName());
	    existingCustomerEntity.setCustomerLastName(customer.getCustomerLastName());
	    existingCustomerEntity.setCustomerEmail(customer.getCustomerEmail());

	    // Hash the password before updating if provided
	    if (customer.getCustomerPassword() != null) {
	        existingCustomerEntity.setCustomerPassword(BCrypt.hashpw(customer.getCustomerPassword(), BCrypt.gensalt()));
	    }

	    // Update the timestamp
	    existingCustomerEntity.setCustomerUpdatedTime(customerRepository.findCurrentTimeStamp());

	    // Save the updated entity
	    CustomerEntity savedCustomerEntity = customerRepository.save(existingCustomerEntity);

	    // Convert and return the updated customer
	    return convertCustomerEntityToCustomer(savedCustomerEntity);
	}
		

	@Override
	public Customer getSingleCustomerInfo(int customerId) {
		CustomerEntity getSingleCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("customer not found with the given ID."));
		return convertCustomerEntityToCustomer(getSingleCustomer);
	}

	@Override
	public Boolean isUserAuthorized(Customer customer) {
		Optional<CustomerEntity> existingCustomerEntity = customerRepository.findBycustomerEmail(customer.getCustomerEmail());

		if (existingCustomerEntity == null) {
			throw new NotFoundException("Entered email not found");
		}

		String storedPassword = existingCustomerEntity.get().getCustomerPassword();

		return BCrypt.checkpw(customer.getCustomerPassword(), storedPassword);
	
	}



}

