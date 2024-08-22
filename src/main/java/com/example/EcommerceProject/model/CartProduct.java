package com.example.EcommerceProject.model;

public class CartProduct {
	
	private int productId;
	private String productName;
	private double productOriginalPrice;
	private int productQuantity;
	private double productUnitPrice;
	private boolean isProductReadyToCheckOut;
	private String errorMessage;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public double getProductOriginalPrice() {
		return productOriginalPrice;
	}
	public void setProductOriginalPrice(double productOriginalPrice) {
		this.productOriginalPrice = productOriginalPrice;
	}
	public double getProductUnitPrice() {
		return productUnitPrice;
	}
	public void setProductUnitPrice(double product_unitPrice) {
		this.productUnitPrice = product_unitPrice;
	}
	public boolean isProductReadyToCheckOut() {
		return isProductReadyToCheckOut;
	}
	public void setProductReadyToCheckOut(boolean isProductReadyToCheckOut) {
		this.isProductReadyToCheckOut = isProductReadyToCheckOut;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	@Override
	public String toString() {
		return "Cartproduct [product_id=" + productId + ", product_name=" + productName + ", product_price="
				+ productOriginalPrice + ", product_quantity=" + productQuantity + ", product_unitPrice=" + productUnitPrice
				+ ", isProductReadyToCheckOut=" + isProductReadyToCheckOut + ", errorMessage=" + errorMessage + "]";
	}
	
	
	
	public CartProduct(int product_id, String product_name, double product_price, int product_quantity) {
		super();
		this.productId = product_id;
		this.productName = product_name;
		this.productOriginalPrice = product_price;
		this.productQuantity = product_quantity;
		//this.product_unitPrice = product_unitPrice;
		//this.isProductReadyToCheckOut = isProductReadyToCheckOut;
		//this.errorMessage = errorMessage;
	}
	
	public CartProduct() {
		super();
	}
	
	
	
	
	
	
	
	
	

}
