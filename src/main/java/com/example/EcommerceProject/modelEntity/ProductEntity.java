package com.example.EcommerceProject.modelEntity;

import java.sql.Timestamp;

import com.example.EcommerceProject.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	private String productDescription;
	
	@Column(name ="product_price")
	private double productPrice;
	
	@Column(name = "product_stock_quantity")
	private int productStockQuantity;
	
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "unit_id")
	private int unitId;
	
	@Column
	private String productImageUrl;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp productCreatedTime;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp productUpdatedTime;
	
	
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
				+ productDescription + ", productPrice=" + productPrice + ", productStockQuantity="
				+ productStockQuantity + ", category_id=" + categoryId + ", unit_id=" + unitId
				+ ", product_image_url=" + productImageUrl + ", product_created_time=" + productCreatedTime
				+ ", product_updated_time=" + productUpdatedTime + "]";
	}
	
	
	public ProductEntity(String productName, String productDescription, double productPrice,
			int productStockQuantity, int categoryId, int unitId, String productImageUrl) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productStockQuantity = productStockQuantity;
		this.categoryId = categoryId;
		this.unitId = unitId;
		this.productImageUrl = productImageUrl;
		
		
	}
	public ProductEntity() {
		super();
	}
	
	
	
	
	
	
	

}
