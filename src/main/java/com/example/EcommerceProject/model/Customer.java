package com.example.EcommerceProject.model;

import java.sql.Timestamp;
import com.example.EcommerceProject.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Customer {
	
	private int customer_id;
	private String customer_first_name;
	private String customer_last_name;
	private String customer_email;
	//@JsonIgnore
	private String customer_password;
	private String customer_address;
	private String customer_phone_number;
	private boolean customer_email_verification;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp customer_created_time;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp customer_updated_time;
    
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_first_name() {
		return customer_first_name;
	}
	public void setCustomer_first_name(String customer_first_name) {
		this.customer_first_name = customer_first_name;
	}
	public String getCustomer_last_name() {
		return customer_last_name;
	}
	public void setCustomer_last_name(String customer_last_name) {
		this.customer_last_name = customer_last_name;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
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
		return "Customer [customer_id=" + customer_id + ", customer_first_name=" + customer_first_name
				+ ", customer_last_name=" + customer_last_name + ", customer_email=" + customer_email
				+ ", customer_password=" + customer_password + ", customer_address=" + customer_address
				+ ", customer_phone_number=" + customer_phone_number + ", customer_email_verification="
				+ customer_email_verification + ", customer_created_time=" + customer_created_time
				+ ", customer_updated_time=" + customer_updated_time + "]";
	}
	
	public Customer(int customer_id, String customer_first_name, String customer_last_name, String customer_email,
			//String customer_password,
			String customer_address, String customer_phone_number,
			boolean customer_email_verification, Timestamp customer_created_time, Timestamp customer_updated_time) {
		super();
		this.customer_id = customer_id;
		this.customer_first_name = customer_first_name;
		this.customer_last_name = customer_last_name;
		this.customer_email = customer_email;
		//this.customer_password = customer_password;
		this.customer_address = customer_address;
		this.customer_phone_number = customer_phone_number;
		this.customer_email_verification = customer_email_verification;
		this.customer_created_time = customer_created_time;
		this.customer_updated_time = customer_updated_time;
	}
	
	public Customer() {
		
	}
	
	
	
	
	
	
    
    

}
