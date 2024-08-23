package com.example.EcommerceProject.modelEntity;

import java.sql.Timestamp;

import com.example.EcommerceProject.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column
	private String product_description;
	
	@Column(name ="product_price")
	private double productPrice;
	
	@Column(name = "product_stock_quantity")
	private int productStockQuantity;
	
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "unit_id")
	private int unitId;
	
	@Column
	private String product_image_url;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp product_created_time;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp product_updated_time;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private CategoryEntity category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitEntity unitEntity;
	
    
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProduct_name() {
		return productName;
	}
	public void setProduct_name(String product_name) {
		this.productName = product_name;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public double getProduct_price() {
		return productPrice;
	}
	public void setProduct_price(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProduct_stock_quantity() {
		return productStockQuantity;
	}
	public void setProduct_stock_quantity(int productStockQuantity) {
		this.productStockQuantity = productStockQuantity;
	}
	public int getCategory_id() {
		return categoryId;
	}
	public void setCategory_id(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getUnit_id() {
		return unitId;
	}
	public void setUnit_id(int unitId) {
		this.unitId = unitId;
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
	public CategoryEntity getCategoryEntity() {
		return category;
	}
	public void setCategoryEntity(CategoryEntity category) {
		this.category = category;
	}
	public UnitEntity getUnitEntity() {
		return unitEntity;
	}
	public void setUnitEntity(UnitEntity unitEntity) {
		this.unitEntity = unitEntity;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", product_name=" + productName + ", product_description="
				+ product_description + ", productPrice=" + productPrice + ", productStockQuantity="
				+ productStockQuantity + ", category_id=" + categoryId + ", unit_id=" + unitId
				+ ", product_image_url=" + product_image_url + ", product_created_time=" + product_created_time
				+ ", product_updated_time=" + product_updated_time + "]";
	}
	
	
	public ProductEntity(String product_name, String product_description, double productPrice,
			int productStockQuantity, int category_id, int unit_id, String product_image_url) {
		super();
		this.productName = product_name;
		this.product_description = product_description;
		this.productPrice = productPrice;
		this.productStockQuantity = productStockQuantity;
		this.categoryId = category_id;
		this.unitId = unit_id;
		this.product_image_url = product_image_url;
		
		
	}
	public ProductEntity() {
		super();
	}
	
	
	
	
	
	
	

}
