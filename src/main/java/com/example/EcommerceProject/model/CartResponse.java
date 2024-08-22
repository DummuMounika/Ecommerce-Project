package com.example.EcommerceProject.model;

import java.util.List;

public class CartResponse {
	
	private List<CartProduct> cartProducts;
	private double totalPrice;
	
	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		return "CartResponse [cartProducts=" + cartProducts + ", totalPrice=" + totalPrice + "]";
	}
	
	public CartResponse(List<CartProduct> cartProducts, double totalPrice) {
		super();
		this.cartProducts = cartProducts;
		this.totalPrice = totalPrice;
	}
	
	public CartResponse() {
		super();
	}
	
	
	
	
	
	
	
	
	
	

}
