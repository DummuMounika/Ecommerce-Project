package com.example.EcommerceProject.model;

import java.sql.Timestamp;

import com.example.EcommerceProject.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Unit {
	
	private int unitId;
	private String unitName;
	private String unitAbbreviation;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp unitCreatedTime;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp unitUpdatedTime;
	
	
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
	

	@Override
	public String toString() {
		return "Unit [unit_id=" + unitId + ", unit_name=" + unitName + ", unit_abbreviation=" + unitAbbreviation
				+ ", unit_created_time=" + unitCreatedTime + ", unit_updated_time=" + unitUpdatedTime + "]";
	}
	
	public Unit(int unitId, String unitName, String unitAbbreviation, Timestamp unitCreatedTime,
			Timestamp unitUpdatedTime) {
		super();
		this.unitId = unitId;
		this.unitName = unitName;
		this.unitAbbreviation = unitAbbreviation;
		this.unitCreatedTime = unitCreatedTime;
		this.unitUpdatedTime = unitUpdatedTime;
	}
	
	public Unit() {
		
	}
	
	
	
	

}
