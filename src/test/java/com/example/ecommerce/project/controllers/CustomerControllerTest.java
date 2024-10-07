package com.example.ecommerce.project.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ecommerce.project.jwt.config.SecurityConfigTest;
import com.example.ecommerce.project.jwt.security.JwtHelper;
import com.example.ecommerce.project.model.Customer;
import com.example.ecommerce.project.services.CustomerService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
//@SpringBootTest
@Import(SecurityConfigTest.class)
@TestPropertySource(properties = "spring.security.enabled=false") // Disable security

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc; // MockMvc simulates HTTP requests

	@MockBean
    private AuthenticationManager authenticationManager;
	
	@MockBean
	private JwtHelper jwtHelper;
	
	@MockBean
	private CustomerService customerService; // Mocking the service layer

	private Customer sampleCustomer;

	@BeforeEach
	void setUp() {
		sampleCustomer = new Customer();
		sampleCustomer.setCustomerFirstName("Monu");
		sampleCustomer.setCustomerLastName("Dummu");
		sampleCustomer.setCustomerEmail("monuD@gmail.com");
		sampleCustomer.setCustomerPassword("monu123");
		sampleCustomer.setCustomerAddress("54326 n mac");
	}

	@Test
	// This annotation simulates an authenticated user
	void testGetAllCustomerDetails() throws Exception {
		List<Customer> customers = Arrays.asList(
		        new Customer(1, "Monu", "Dummu", "monuD@gmail.com", "monu123", "54326 n mac", false, null, null),
		        new Customer(2, "Venu", "Dummu", "venuD@gmail.com", "venu123", "5313 n mac", false, null, null)
		    );
		 when(customerService.getAllCustomerDetails()).thenReturn(customers);
		
		 mockMvc.perform(get("/public/customers"))
		 .andExpect(status().isOk())
		 .andExpect(jsonPath("$[0].customerFirstName").value("Monu"))
			.andExpect(jsonPath("$[0].customerLastName").value("Dummu"))
			.andExpect(jsonPath("$[0].customerEmail").value("monuD@gmail.com"))
			.andExpect(jsonPath("$[1].customerFirstName").value("Venu"))
			.andExpect(jsonPath("$[1].customerLastName").value("Dummu"))
			.andExpect(jsonPath("$[1].customerEmail").value("venuD@gmail.com"));
	}

	@Test
	void testGetSingleCustomerDetail() throws Exception {
		when(customerService.getSingleCustomerInfo(1)).thenReturn(sampleCustomer);
	    
	    mockMvc.perform(get("/public/customers/1"))
	           .andExpect(status().isOk())
	           .andExpect(content().json("{\"customerFirstName\":\"Monu\",\"customerLastName\":\"Dummu\",\"customerEmail\":\"monuD@gmail.com\"}"));
	}

	@Test
	void testCreateCustomer() throws Exception{
		Customer createdCustomer = new Customer(3, "Akhi", "Gorakala", "akhiG@gmail.com", "newpassword", "123 New St", true, null, null);
		 when(customerService.addCustomerInfo(any(Customer.class))).thenReturn(true);
		 
		 mockMvc.perform(post("/customers")
		            .contentType("application/json")
		            .content("{\"customerFirstName\":\"New\",\"customerLastName\":\"Customer\",\"customerEmail\":\"newcustomer@gmail.com\",\"customerPassword\":\"newpassword\",\"customerAddress\":\"123 New St\"}"))
		            .andExpect(status().isCreated())
		            .andExpect(jsonPath("$.customerFirstName").value("Akhi"))
		            .andExpect(jsonPath("$.customerLastName").value("Gorakala"))
		            .andExpect(jsonPath("$.customerEmail").value("akhiG@gmail.com"));
		}
	    

	@Test
	void testDeleteCustomerDetail() throws Exception {
		when(customerService.deleteCustomerInfo(1)).thenReturn("Deleted successfully" );
		mockMvc.perform(delete("/public/customers/1"))
		.andExpect(status().isOk());
	}

	@Test
	void testUpdateCustomerDetail() throws Exception {
		 when(customerService.updateCustomerInfo(any(Customer.class), anyInt())).thenReturn(sampleCustomer);
		    
		    mockMvc.perform(post("/public/customers/1") // Use the appropriate method for updating
		           .contentType("application/json")
		           .content("{\"customerFirstName\":\"Updated\",\"customerLastName\":\"Dummu\",\"customerEmail\":\"monuD@gmail.com\"}")) // Update fields as needed
		           .andExpect(status().isOk())
		           .andExpect(jsonPath("$.customerFirstName").value("Updated"))
		           .andExpect(jsonPath("$.customerLastName").value("Dummu"))
		           .andExpect(jsonPath("$.customerEmail").value("monuD@gmail.com"));
		}

}
