package com.example.ecommerce.project.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.example.ecommerce.project.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Customer  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	private String customerPassword;
	private String customerAddress;
	private String customerPhoneNumber;
	private boolean customerEmailVerification;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp customerCreatedTime;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp customerUpdatedTime;
    
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public boolean isCustomerEmailVerification() {
		return customerEmailVerification;
	}
	public void setCustomerEmailVerification(boolean customerEmailVerification) {
		this.customerEmailVerification = customerEmailVerification;
	}
	public Timestamp getCustomerCreatedTime() {
		return customerCreatedTime;
	}
	public void setCustomerCreatedTime(Timestamp customerCreatedTime) {
		this.customerCreatedTime = customerCreatedTime;
	}
	public Timestamp getCustomerUpdatedTime() {
		return customerUpdatedTime;
	}
	public void setCustomerUpdatedTime(Timestamp customerUpdatedTime) {
		this.customerUpdatedTime = customerUpdatedTime;
	}
	
	@Override
	public String toString() {
		return "Customer [customer_id=" + customerId + ", customer_first_name=" + customerFirstName
				+ ", customer_last_name=" + customerLastName + ", customer_email=" + customerEmail
				+ ", customer_password=" + customerPassword + ", customer_address=" + customerAddress
				+ ", customer_phone_number=" + customerPhoneNumber + ", customer_email_verification="
				+ customerEmailVerification + ", customer_created_time=" + customerCreatedTime
				+ ", customer_updated_time=" + customerUpdatedTime + "]";
	}
	
	public Customer(int customerId, String customerFirstName, String customerLastName, String customerEmail,
			String customerAddress, String customerPhoneNumber,boolean customerEmailVerification, Timestamp customerCreatedTime, Timestamp customerUpdatedTime) {
		super();
		this.customerId = customerId;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmailVerification = customerEmailVerification;
		this.customerCreatedTime = customerCreatedTime;
		this.customerUpdatedTime = customerUpdatedTime;
	}
	
	public Customer() {
		
	}
	
	
	
	
	
	
    
    

}
