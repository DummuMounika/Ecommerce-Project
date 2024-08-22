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
	private Timestamp unit_created_time;
	
    @JsonSerialize(using = JsonTimestampSerializer.class)
	@Column
	private Timestamp unit_updated_time;
    
    @OneToMany(mappedBy="unitEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ProductEntity> productsEntities;
	
	public int getUnit_id() {
		return unitId;
	}
	public void setUnit_id(int unitId) {
		this.unitId = unitId;
	}
	public String getUnit_name() {
		return unitName;
	}
	public void setUnit_name(String unitName) {
		this.unitName = unitName;
	}
	public String getUnit_abbreviation() {
		return unitAbbreviation;
	}
	public void setUnit_abbreviation(String unitAbbreviation) {
		this.unitAbbreviation = unitAbbreviation;
	}
	public Timestamp getUnit_created_time() {
		return unit_created_time;
	}
	public void setUnit_created_time(Timestamp unit_created_time) {
		this.unit_created_time = unit_created_time;
	}
	public Timestamp getUnit_updated_time() {
		return unit_updated_time;
	}
	public void setUnit_updated_time(Timestamp unit_updated_time) {
		this.unit_updated_time = unit_updated_time;
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
				+ ", unit_created_time=" + unit_created_time + ", unit_updated_time=" + unit_updated_time + "]";
	}
	public UnitEntity( String unitName, String unitAbbreviation) {
		super();
		this.unitName = unitName;
		this.unitAbbreviation = unitAbbreviation;
	}
	
	public UnitEntity() {
		
	}

}
