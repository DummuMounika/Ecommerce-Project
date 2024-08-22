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
@Table(name="add_To_Cart")
public class AddToCartEntity {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int cart_id;
	 
	 @Column(name = "customer_id")
	 private int customerId;
	 
	 @Column(name = "product_id")
	 private int productId;
	 
	 @Column
	 private int quantity;
	 
	 @Column
	 private double unit_price;
	 
	 @Column
	 @JsonSerialize(using = JsonTimestampSerializer.class)
	 private Timestamp cart_created_time;
	 
	 @Column
	 @JsonSerialize(using = JsonTimestampSerializer.class)
	 private Timestamp cart_updated_time;
	 
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getCustomer_id() {
		return customerId;
	}
	public void setCustomer_id(int customer_id) {
		this.customerId = customer_id;
	}
	public int getProduct_id() {
		return productId;
	}
	public void setProduct_id(int productId) {
		this.productId = productId;
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
		return "AddToCartEntity [cart_id=" + cart_id + ", customer_id=" + customerId + ", productId=" + productId
				+ ", quantity=" + quantity + ", unit_price=" + unit_price + ", cart_created_time=" + cart_created_time
				+ ", cart_updated_time=" + cart_updated_time + "]";
	}
	
	public AddToCartEntity(int customer_id, int productId, int quantity, double unit_price) {
		super();
		//this.cart_id = cart_id;
		this.customerId = customer_id;
		this.productId = productId;
		this.quantity = quantity;
		this.unit_price = unit_price;
	}
	
	public AddToCartEntity() {
		super();
	}
	
	

}
