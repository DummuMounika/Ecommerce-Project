package com.example.ecommerce.project.model;

import java.sql.Timestamp;
import java.util.List;

import com.example.ecommerce.project.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Category {
	
	private int categoryId;
	private String categoryName;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp categoryCreatedTime;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp categoryUpdatedTime;
	
	private List<Product> products;
    
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Timestamp getCategoryCreatedTime() {
		return categoryCreatedTime;
	}
	public void setCategoryCreatedTime(Timestamp categoryCreatedTime) {
		this.categoryCreatedTime = categoryCreatedTime;
	}
	public Timestamp getCategoryUpdatedTime() {
		return categoryUpdatedTime;
	}
	public void setCategoryUpdatedTime(Timestamp categoryUpdatedTime) {
		this.categoryUpdatedTime = categoryUpdatedTime;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		return "Category [category_id=" + categoryId + ", category_name=" + categoryName + ", category_created_time="
				+ categoryCreatedTime + ", category_updated_time=" + categoryUpdatedTime + "]";
	}
	
	public Category(int categoryId, String categoryName, List<Product> productList, Timestamp categoryCreatedTime,
			Timestamp categoryUpdatedTime) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.products = productList;
		this.categoryCreatedTime = categoryCreatedTime;
		this.categoryUpdatedTime = categoryUpdatedTime;
	}
	
	public Category() {
	}
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
	
	
    
    
	

}
