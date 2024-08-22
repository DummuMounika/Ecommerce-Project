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
	private String category_name;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp category_created_time;
	
	@Column
	@JsonSerialize(using = JsonTimestampSerializer.class)
    private Timestamp category_updated_time;
	
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ProductEntity> productsEntities;
    
	public int getCategory_id() {
		return categoryId;
	}
	public void setCategory_id(int categoryId) {
		this.categoryId = categoryId;
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
	
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", category_name=" + category_name + ", category_created_time="
				+ category_created_time + ", category_updated_time=" + category_updated_time + "]";
	}
	
	public CategoryEntity(String category_name) {
		super();
		this.category_name = category_name;
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
