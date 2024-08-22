package com.example.EcommerceProject.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.EcommerceProject.exceptions.NotFoundException;
import com.example.EcommerceProject.model.Customer;
import com.example.EcommerceProject.modelEntity.CustomerEntity;
import com.example.EcommerceProject.repository.CustomerRepository;

@Service
public class CustomerServiceImplement implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;


	//convertMethods
	private Customer convertCustomerEntityToCustomer(CustomerEntity customerEntity) {
		return new Customer(customerEntity.getCustomer_id(),customerEntity.getCustomer_first_name(),
				customerEntity.getCustomer_last_name(),customerEntity.getCustomer_email(),
				//customerEntity.getCustomer_password()
				customerEntity.getCustomer_address(),customerEntity.getCustomer_phone_number(),customerEntity.isCustomer_email_verification(),
				customerEntity.getCustomer_created_time(),customerEntity.getCustomer_updated_time());
	}

	private List<Customer> convertCustomerEntityListToCustomerList(List<CustomerEntity> customerEntityList){
		List<Customer> customerList = new ArrayList<Customer>();
		for(CustomerEntity customerEntity: customerEntityList) {
			Customer customer = convertCustomerEntityToCustomer(customerEntity);
			customerList.add(customer);
		}
		return customerList;
	}

	private CustomerEntity convertCustomerToCustomerEntity(Customer customer) {
		return new CustomerEntity(customer.getCustomer_first_name(),customer.getCustomer_last_name(),customer.getCustomer_email(),
				//customer.getCustomer_password(),
				customer.getCustomer_address(),customer.getCustomer_phone_number(),customer.isCustomer_email_verification());
	}


	//CRUD  Methods
	@Override
	public List<Customer> getAllCustomerDetails() {
		List<CustomerEntity> customerEntityList = customerRepository.findAll();
		List<Customer> customerList = convertCustomerEntityListToCustomerList(customerEntityList);
		return customerList;
	}

	@Override
	public boolean addCustomerInfo(Customer customer) {
		CustomerEntity customerEntity = convertCustomerToCustomerEntity(customer);
		//Hash the password
		customerEntity.setCustomer_password(BCrypt.hashpw(customerEntity.getCustomer_password(), BCrypt.gensalt()));
		//method to set created and update time
		Timestamp currentTimestamp = customerRepository.findCurrentTimeStamp();
		customerEntity.setCustomer_created_time(currentTimestamp);
		customerEntity.setCustomer_updated_time(currentTimestamp);
		CustomerEntity entity = customerRepository.save(customerEntity);	
		if (entity != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String deleteCustomerInfo(int customerId) {
		CustomerEntity removeCustomerEntity = customerRepository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("customer Id not found"));
		customerRepository.delete(removeCustomerEntity);
		return "The " + customerId + " deleted successfully" ;
	}

	@Override
	public Customer updateCustomerInfo(Customer customer, int customerId) {
		CustomerEntity existingCustomerEntity = customerRepository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("customer Id not found"));
		existingCustomerEntity.setCustomer_first_name(customer.getCustomer_first_name());
		existingCustomerEntity.setCustomer_last_name(customer.getCustomer_last_name());
		existingCustomerEntity.setCustomer_email(customer.getCustomer_email());

		//Hash the password before updating
		if(customer.getCustomer_password() != null) {
			existingCustomerEntity.setCustomer_password(BCrypt.hashpw(customer.getCustomer_password(), BCrypt.gensalt()));
		}
		existingCustomerEntity.setCustomer_updated_time(customerRepository.findCurrentTimeStamp());
		CustomerEntity saveCustomerEntity = customerRepository.save(existingCustomerEntity);
		return convertCustomerEntityToCustomer(saveCustomerEntity);
	}

	@Override
	public Customer getSingleCustomerInfo(int customerId) {
		CustomerEntity getSingleCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("customer Id not found"));
		return convertCustomerEntityToCustomer(getSingleCustomer);
	}

	@Override
	public Boolean isUserAuthorized(Customer customer) {
		CustomerEntity existingCustomerEntity = customerRepository.findBycustomerEmail(customer.getCustomer_email());

		if (existingCustomerEntity == null) {
			throw new NotFoundException("Entered email not found");
		}

		String storedPassword = existingCustomerEntity.getCustomer_password();

		if (BCrypt.checkpw(customer.getCustomer_password(), storedPassword)) {
			return true;
		} else {
			return false;
		}
	}



}

