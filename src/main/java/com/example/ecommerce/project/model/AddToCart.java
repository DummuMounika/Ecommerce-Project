package com.example.ecommerce.project.model;

import java.sql.Timestamp;

import com.example.ecommerce.project.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class AddToCart {
	
	 private int cartId;
	 private int customerId;
	 private int productId;
	 private int quantity;
	 private double unitPrice;
	 @JsonSerialize(using = JsonTimestampSerializer.class)
	 private Timestamp cartCreatedTime;
	 @JsonSerialize(using = JsonTimestampSerializer.class)
	 private Timestamp cartUpdatedTime;
	 
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Timestamp getCartCreatedTime() {
		return cartCreatedTime;
	}
	public void setCartCreatedTime(Timestamp cartCreatedTime) {
		this.cartCreatedTime = cartCreatedTime;
	}
	public Timestamp getCartUpdatedTime() {
		return cartUpdatedTime;
	}
	public void setCartUpdatedTime(Timestamp cartUpdatedTime) {
		this.cartUpdatedTime = cartUpdatedTime;
	}
	
	@Override
	public String toString() {
		return "AddToCart [cart_id=" + cartId + ", customer_id=" + customerId + ", product_id=" + productId
				+ ", quantity=" + quantity + ", unit_price=" + unitPrice + ", cart_created_time=" + cartCreatedTime
				+ ", cart_updated_time=" + cartUpdatedTime + "]";
	}
	public AddToCart(int cartId, int customerId, int productId, int quantity, double unitPrice,
			Timestamp cartCreatedTime, Timestamp cartUpdatedTime) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.cartCreatedTime = cartCreatedTime;
		this.cartUpdatedTime = cartUpdatedTime;
	}
	
	public AddToCart() {
	}
	
	 
	
	 
	 

}
