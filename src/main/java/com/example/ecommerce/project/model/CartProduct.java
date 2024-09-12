package com.example.ecommerce.project.model;

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
	public void setProductUnitPrice(double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
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
	
	
	
	public CartProduct(int productId, String productName, double productPrice, int productQuantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productOriginalPrice = productPrice;
		this.productQuantity = productQuantity;
	}
	
	public CartProduct() {
		super();
	}
	
	
	
	
	
	
	
	
	

}
