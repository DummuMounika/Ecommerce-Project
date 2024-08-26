package com.example.EcommerceProject.model;

import java.sql.Timestamp;

import com.example.EcommerceProject.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Product {

	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	private int productStockQuantity;
	@JsonIgnore
	private int categoryId;
	private String categoryName;
	@JsonIgnore
	private int unitId;
	private String unitName;
	private String productImageUrl;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp productCreatedTime;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp productUpdatedTime;
    
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
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductStockQuantity() {
		return productStockQuantity;
	}
	public void setProductStockQuantity(int productStockQuantity) {
		this.productStockQuantity = productStockQuantity;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	public Timestamp getProductCreatedTime() {
		return productCreatedTime;
	}
	public void setProductCreatedTime(Timestamp productCreatedTime) {
		this.productCreatedTime = productCreatedTime;
	}
	public Timestamp getProductUpdatedTime() {
		return productUpdatedTime;
	}
	public void setProductUpdatedTime(Timestamp productUpdatedTime) {
		this.productUpdatedTime = productUpdatedTime;
	}
	
	
	@Override
	public String toString() {
		return "Product [product_id=" + productId + ", product_name=" + productName + ", product_description="
				+ productDescription + ", product_price=" + productPrice + ", product_stock_quantity="
				+ productStockQuantity + ", category_id=" + categoryId + ", category_name=" + categoryName
				+ ", unit_id=" + unitId + ", product_image_url=" + productImageUrl + ", product_created_time="
				+ productCreatedTime + ", product_updated_time=" + productUpdatedTime + "]";
	}
	
	
	public Product(int productId, String productName, String productDescription, double productPrice,
			int productStockQuantity, int categoryId, String categoryName,int unitId,String unitName, String productImageUrl,
			Timestamp productCreatedTime, Timestamp productUpdatedTime) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productStockQuantity = productStockQuantity;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.unitId = unitId;
		this.unitName = unitName;
		this.productImageUrl = productImageUrl;
		this.productCreatedTime = productCreatedTime;
		this.productUpdatedTime = productUpdatedTime;
	}
	public Product() {
		
	}
	
	
	
	
	
	
    
    
}
