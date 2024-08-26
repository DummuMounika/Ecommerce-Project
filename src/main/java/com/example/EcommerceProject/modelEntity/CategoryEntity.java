package com.example.EcommerceProject.modelEntity;

import java.sql.Timestamp;
import java.util.List;

import com.example.EcommerceProject.utils.JsonTimestampSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="categories")
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp categoryCreatedTime;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp categoryUpdatedTime;
	
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ProductEntity> productsEntities;
    
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
	
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", category_name=" + categoryName + ", category_created_time="
				+ categoryCreatedTime + ", category_updated_time=" + categoryUpdatedTime + "]";
	}
	
	public CategoryEntity(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	
	public CategoryEntity() {
		
	}
	public List<ProductEntity> getProductEntity() {
		return productsEntities;
	}
	public void setProductEntity(List<ProductEntity> productsEntities) {
		this.productsEntities = productsEntities;
	}
	
	
	
	

}
