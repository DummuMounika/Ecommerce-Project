package com.example.EcommerceProject.model;

import java.sql.Timestamp;
import java.util.List;

import com.example.EcommerceProject.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Category {
	
	private int category_id;
	private String category_name;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp category_created_time;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp category_updated_time;
	
	private List<Product> products;
    
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public Timestamp getCategory_created_time() {
		return category_created_time;
	}
	public void setCategory_created_time(Timestamp category_created_time) {
		this.category_created_time = category_created_time;
	}
	public Timestamp getCategory_updated_time() {
		return category_updated_time;
	}
	public void setCategory_updated_time(Timestamp category_updated_time) {
		this.category_updated_time = category_updated_time;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name=" + category_name + ", category_created_time="
				+ category_created_time + ", category_updated_time=" + category_updated_time + "]";
	}
	
	public Category(int category_id, String category_name, List<Product> productList, Timestamp category_created_time,
			Timestamp category_updated_time) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.products = productList;
		this.category_created_time = category_created_time;
		this.category_updated_time = category_updated_time;
	}
	
	public Category() {
	}
	
	public Category(String category_name) {
		this.category_name = category_name;
	}
	
	
	
	
	
    
    
	

}
