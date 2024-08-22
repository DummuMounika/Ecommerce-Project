package com.example.EcommerceProject.modelEntity;

import java.sql.Timestamp;

import com.example.EcommerceProject.utils.JsonTimestampSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class CustomerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	
	@Column(name="customer_first_name")
	private String customerFirstName;
	
	@Column
	private String customer_last_name;
	
	@Column(name = "customer_email")
	private String customerEmail;
	
	@Column
	private String customer_password;
	
	@Column
	private String customer_address;
	
	@Column
	private String customer_phone_number;
	
	@Column
	private boolean customer_email_verification;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp customer_created_time;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp customer_updated_time;
    
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_first_name() {
		return customerFirstName;
	}
	public void setCustomer_first_name(String CustomerFirstName) {
		this.customerFirstName = CustomerFirstName;
	}
	public String getCustomer_last_name() {
		return customer_last_name;
	}
	public void setCustomer_last_name(String customer_last_name) {
		this.customer_last_name = customer_last_name;
	}
	public String getCustomer_email() {
		return customerEmail;
	}
	public void setCustomer_email(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomer_password() {
		return customer_password;
	}
	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCustomer_phone_number() {
		return customer_phone_number;
	}
	public void setCustomer_phone_number(String customer_phone_number) {
		this.customer_phone_number = customer_phone_number;
	}
	public boolean isCustomer_email_verification() {
		return customer_email_verification;
	}
	public void setCustomer_email_verification(boolean customer_email_verification) {
		this.customer_email_verification = customer_email_verification;
	}
	public Timestamp getCustomer_created_time() {
		return customer_created_time;
	}
	public void setCustomer_created_time(Timestamp customer_created_time) {
		this.customer_created_time = customer_created_time;
	}
	public Timestamp getCustomer_updated_time() {
		return customer_updated_time;
	}
	public void setCustomer_updated_time(Timestamp customer_updated_time) {
		this.customer_updated_time = customer_updated_time;
	}
	
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", CustomerFirstName=" + customerFirstName
				+ ", customer_last_name=" + customer_last_name + ", customerEmail=" + customerEmail
				+ ", customer_password=" + customer_password + ", customer_address=" + customer_address
				+ ", customer_phone_number=" + customer_phone_number + ", customer_email_verification="
				+ customer_email_verification + ", customer_created_time=" + customer_created_time
				+ ", customer_updated_time=" + customer_updated_time + "]";
	}
	
	public CustomerEntity(String customerFirstName, String customer_last_name, String customerEmail,
			//String customer_password, 
			String customer_address, String customer_phone_number,
			boolean customer_email_verification) {
		super();
		this.customerFirstName = customerFirstName;
		this.customer_last_name = customer_last_name;
		this.customerEmail = customerEmail;
		//this.customer_password = customer_password;
		this.customer_address = customer_address;
		this.customer_phone_number = customer_phone_number;
		this.customer_email_verification = customer_email_verification;
	}
	
	public CustomerEntity() {
		super();
	}
	
	
	
	
	

}
