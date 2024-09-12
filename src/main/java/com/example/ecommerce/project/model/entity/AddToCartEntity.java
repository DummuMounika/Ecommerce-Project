package com.example.ecommerce.project.model.entity;

import java.sql.Timestamp;

import com.example.ecommerce.project.utils.JsonTimestampSerializer;
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
	 private int cartId;
	 
	 @Column(name = "customer_id")
	 private int customerId;
	 
	 @Column(name = "product_id")
	 private int productId;
	 
	 @Column
	 private int quantity;
	 
	 @Column
	 private double unitPrice;
	 
	 @Column
	 @JsonSerialize(using = JsonTimestampSerializer.class)
	 private Timestamp cartCreatedTime;
	 
	 @Column
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
		return "AddToCartEntity [cart_id=" + cartId + ", customer_id=" + customerId + ", productId=" + productId
				+ ", quantity=" + quantity + ", unit_price=" + unitPrice + ", cart_created_time=" + cartCreatedTime
				+ ", cart_updated_time=" + cartUpdatedTime + "]";
	}
	
	public AddToCartEntity(int customerId, int productId, int quantity, double unitPrice) {
		super();
		this.customerId = customerId;
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	public AddToCartEntity() {
		super();
	}
	
	

}
