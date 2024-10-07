package com.example.ecommerce.project.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.ecommerce.project.model.entity.CustomerEntity;
import com.example.ecommerce.project.repository.CustomerRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceImplementTest {
	
	@Autowired
    private CustomerRepository customerRepository;
    
    private CustomerEntity sampleCustomer;
    
	@BeforeEach
    void setUp() {
        sampleCustomer = new CustomerEntity();
        sampleCustomer.setCustomerFirstName("Venuu");
        sampleCustomer.setCustomerLastName("Dummu");
        sampleCustomer.setCustomerEmail("venuD@gmail.com");
        sampleCustomer.setCustomerPassword("venu123");
        sampleCustomer.setCustomerAddress("54328 n mac");
        customerRepository.save(sampleCustomer);
    }

	@DisplayName("Test 1: Reterive the customer details")
    @Order(1)
	@Test
	void testGetAllCustomerDetails() {
		assertEquals(sampleCustomer.getCustomerAddress(), "54328 n mac");
		assertEquals(sampleCustomer.getCustomerFirstName(), "Venuu");
		assertEquals(sampleCustomer.getCustomerLastName(), "Dummu");
		Assertions.assertThat(sampleCustomer.getCustomerId()).isEqualTo(1);
		List<CustomerEntity> customer = customerRepository.findAll();
		Assertions.assertThat(customer.size()).isGreaterThan(0);
	}

	@DisplayName("Test 2: Add customer details")
    @Order(2)
	@Test
	void testAddCustomerInfo() {
		sampleCustomer = new CustomerEntity();
        sampleCustomer.setCustomerFirstName("Venuu1");
        sampleCustomer.setCustomerLastName("Dummu1");
        sampleCustomer.setCustomerEmail("venuD1@gmail.com");
        sampleCustomer.setCustomerPassword("venu1233");
        sampleCustomer.setCustomerAddress("54328 n mac");
        CustomerEntity savedCustomer = customerRepository.save(sampleCustomer);
        
        assertNotNull(savedCustomer);
        assertEquals("Venuu1", savedCustomer.getCustomerFirstName());
        assertEquals("Dummu1", savedCustomer.getCustomerLastName());
        assertEquals("venuD1@gmail.com", savedCustomer.getCustomerEmail());
        assertEquals("54328 n mac", savedCustomer.getCustomerAddress());
	}

	@DisplayName("Test 3: Delete customer details")
    @Order(3)
	@Test
	void testDeleteCustomerInfo() {
		customerRepository.delete(sampleCustomer);

	    boolean exists = customerRepository.findById(sampleCustomer.getCustomerId()).isPresent();
	    
		assertEquals(false, exists );
		
	}

	@DisplayName("Test 4: Update customer details")
    @Order(4)
	@Test
	void testUpdateCustomerInfo() {
		sampleCustomer.setCustomerFirstName("UpdatedName");
	    customerRepository.save(sampleCustomer);
	    
	    CustomerEntity updatedCustomer = customerRepository.findById(sampleCustomer.getCustomerId()).orElse(null);
	    
	    assertEquals("UpdatedName", updatedCustomer.getCustomerFirstName());
	}

	@DisplayName("Test 5: single customer details")
    @Order(5)
	@Test
	void testGetSingleCustomerInfo() {
		CustomerEntity retrievedCustomer = customerRepository.findById(sampleCustomer.getCustomerId()).orElse(null);
	    
	    assertEquals(sampleCustomer.getCustomerId(), retrievedCustomer.getCustomerId());
	    assertEquals("Venuu", retrievedCustomer.getCustomerFirstName());
	}

}
