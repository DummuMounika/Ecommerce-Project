package com.example.EcommerceProject.model;

import java.sql.Timestamp;

import com.example.EcommerceProject.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Product {

	private int product_id;
	private String product_name;
	private String product_description;
	private double product_price;
	private int product_stock_quantity;
	@JsonIgnore
	private int category_id;
	private String category_name;
	@JsonIgnore
	private int unit_id;
	private String unit_name;
	private String product_image_url;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp product_created_time;
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp product_updated_time;
    
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public int getProduct_stock_quantity() {
		return product_stock_quantity;
	}
	public void setProduct_stock_quantity(int product_stock_quantity) {
		this.product_stock_quantity = product_stock_quantity;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getProduct_image_url() {
		return product_image_url;
	}
	public void setProduct_image_url(String product_image_url) {
		this.product_image_url = product_image_url;
	}
	public Timestamp getProduct_created_time() {
		return product_created_time;
	}
	public void setProduct_created_time(Timestamp product_created_time) {
		this.product_created_time = product_created_time;
	}
	public Timestamp getProduct_updated_time() {
		return product_updated_time;
	}
	public void setProduct_updated_time(Timestamp product_updated_time) {
		this.product_updated_time = product_updated_time;
	}
	
	
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_description="
				+ product_description + ", product_price=" + product_price + ", product_stock_quantity="
				+ product_stock_quantity + ", category_id=" + category_id + ", category_name=" + category_name
				+ ", unit_id=" + unit_id + ", product_image_url=" + product_image_url + ", product_created_time="
				+ product_created_time + ", product_updated_time=" + product_updated_time + "]";
	}
	
	
	public Product(int product_id, String product_name, String product_description, double product_price,
			int product_stock_quantity, int category_id, String category_name,int unit_id,String unit_name, String product_image_url,
			Timestamp product_created_time, Timestamp product_updated_time) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_description = product_description;
		this.product_price = product_price;
		this.product_stock_quantity = product_stock_quantity;
		this.category_id = category_id;
		this.category_name = category_name;
		this.unit_id = unit_id;
		this.unit_name = unit_name;
		this.product_image_url = product_image_url;
		this.product_created_time = product_created_time;
		this.product_updated_time = product_updated_time;
	}
	public Product() {
		
	}
	
	
	
	
	
	
    
    
}
