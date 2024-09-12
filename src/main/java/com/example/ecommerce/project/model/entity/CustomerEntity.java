package com.example.ecommerce.project.model.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ecommerce.project.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class CustomerEntity implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	
	@Column(name="customer_first_name")
	private String customerFirstName;
	
	@Column
	private String customerLastName;
	
	@Column(name = "customer_email")
	private String customerEmail;
	
	@Column
	private String customerPassword;
	
	@Column
	private String customerAddress;
	
	@Column
	private String customerPhoneNumber;
	
	@Column
	private boolean customerEmailVerification;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp customerCreatedTime;
	
	@Column
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
		return "Customer [customer_id=" + customerId + ", CustomerFirstName=" + customerFirstName
				+ ", customer_last_name=" + customerLastName + ", customerEmail=" + customerEmail
				+ ", customer_password=" + customerPassword + ", customer_address=" + customerAddress
				+ ", customer_phone_number=" + customerPhoneNumber + ", customer_email_verification="
				+ customerEmailVerification + ", customer_created_time=" + customerCreatedTime
				+ ", customer_updated_time=" + customerUpdatedTime + "]";
	}
	
	public CustomerEntity(String customerFirstName, String customerLastName, String customerEmail,
			String customerAddress, String customerPhoneNumber,boolean customerEmailVerification) {
		super();
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmailVerification = customerEmailVerification;
	}
	
	public CustomerEntity() {
		super();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}
	@Override
	public String getPassword() {
		return customerPassword;
	}
	@Override
	public String getUsername() {
		return customerEmail;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
	
	
	
	

}
