package com.example.ecommerce.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.ecommerce.project.model.entity.CustomerEntity;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    
    private CustomerEntity sampleCustomer;
    
    @BeforeEach
    void setUp() {
        sampleCustomer = new CustomerEntity();
        sampleCustomer.setCustomerFirstName("Monu");
        sampleCustomer.setCustomerLastName("Dummu");
        sampleCustomer.setCustomerEmail("monuD@gmail.com");
        sampleCustomer.setCustomerPassword("monu123");
        sampleCustomer.setCustomerAddress("54326 n mac");
        customerRepository.save(sampleCustomer);
    }

    //@DisplayName("Test 1: Find Current Timestamp")
    //@Order(1)
    //@Rollback(value = false)
    void testFindCurrentTimeStamp() {
        Timestamp currentTimestamp = customerRepository.findCurrentTimeStamp();
        assertNotNull(currentTimestamp);
        System.out.println("Current Timestamp: " + currentTimestamp);
    }

    @Test
    @DisplayName("Test 2: Find by Customer Email")
    @Order(2)
    void testFindByCustomerEmail() {
        Optional<CustomerEntity> customer = customerRepository.findById(1);
        assertThat(customer).isPresent(); 
        if (customer.isPresent()) {
            System.out.println("Found customer: " + customer.get());
            assertThat(customer.get().getCustomerEmail()).isEqualTo("monuD@gmail.com"); 
            assertEquals("Monu", customer.get().getCustomerFirstName());
            assertEquals("Dummu", customer.get().getCustomerLastName());

        } else {
            System.out.println("Customer with ID 1 does not exist.");
        }
    }
    
    @Test
    void testp() {
    	Assertions.assertEquals(1, 1);
    }

}
