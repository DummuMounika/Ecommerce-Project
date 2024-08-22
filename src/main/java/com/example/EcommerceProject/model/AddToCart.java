package com.example.EcommerceProject.model;

import java.sql.Timestamp;
import com.example.EcommerceProject.utils.JsonTimestampSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class AddToCart {
	
	 private int cart_id;
	 private int customer_id;
	 private int product_id;
	 private int quantity;
	 private double unit_price;
	 @JsonSerialize(using = JsonTimestampSerializer.class)
	 private Timestamp cart_created_time;
	 @JsonSerialize(using = JsonTimestampSerializer.class)
	 private Timestamp cart_updated_time;
	 
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public Timestamp getCart_created_time() {
		return cart_created_time;
	}
	public void setCart_created_time(Timestamp cart_created_time) {
		this.cart_created_time = cart_created_time;
	}
	public Timestamp getCart_updated_time() {
		return cart_updated_time;
	}
	public void setCart_updated_time(Timestamp cart_updated_time) {
		this.cart_updated_time = cart_updated_time;
	}
	
	@Override
	public String toString() {
		return "AddToCart [cart_id=" + cart_id + ", customer_id=" + customer_id + ", product_id=" + product_id
				+ ", quantity=" + quantity + ", unit_price=" + unit_price + ", cart_created_time=" + cart_created_time
				+ ", cart_updated_time=" + cart_updated_time + "]";
	}
	public AddToCart(int cart_id, int customer_id, int product_id, int quantity, double unit_price,
			Timestamp cart_created_time, Timestamp cart_updated_time) {
		super();
		this.cart_id = cart_id;
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.cart_created_time = cart_created_time;
		this.cart_updated_time = cart_updated_time;
	}
	
	public AddToCart() {
	}
	
	 
	
	 
	 

}
