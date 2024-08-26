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
@Table(name="units")
public class UnitEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="unit_id")
	private int unitId;
	
	@Column(name="unit_name")
	private String unitName;
	
	@Column(name="unit_abbreviation")
	private String unitAbbreviation;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column
	private Timestamp unitCreatedTime;
	
    @JsonSerialize(using = JsonTimestampSerializer.class)
	@Column
	private Timestamp unitUpdatedTime;
    
    @OneToMany(mappedBy="unitEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ProductEntity> productsEntities;
	
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
	public String getUnitAbbreviation() {
		return unitAbbreviation;
	}
	public void setUnitAbbreviation(String unitAbbreviation) {
		this.unitAbbreviation = unitAbbreviation;
	}
	public Timestamp getUnitCreatedTime() {
		return unitCreatedTime;
	}
	public void setUnitCreatedTime(Timestamp unitCreatedTime) {
		this.unitCreatedTime = unitCreatedTime;
	}
	public Timestamp getUnitUpdatedTime() {
		return unitUpdatedTime;
	}
	public void setUnitUpdatedTime(Timestamp unitUpdatedTime) {
		this.unitUpdatedTime = unitUpdatedTime;
	}
	public List<ProductEntity> getProductsEntities() {
		return productsEntities;
	}
	public void setProductsEntities(List<ProductEntity> productsEntities) {
		this.productsEntities = productsEntities;
	}
	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", unitName=" + unitName + ", unitAbbreviation=" + unitAbbreviation
				+ ", unit_created_time=" + unitCreatedTime + ", unit_updated_time=" + unitUpdatedTime + "]";
	}
	public UnitEntity( String unitName, String unitAbbreviation) {
		super();
		this.unitName = unitName;
		this.unitAbbreviation = unitAbbreviation;
	}
	
	public UnitEntity() {
		
	}

}
