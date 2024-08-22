package com.example.EcommerceProject.model;

import java.sql.Timestamp;
import java.util.List;

import com.example.EcommerceProject.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Unit {
	
	private int unit_id;
	private String unit_name;
	private String unit_abbreviation;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp unit_created_time;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp unit_updated_time;
	
	
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
	public String getUnit_abbreviation() {
		return unit_abbreviation;
	}
	public void setUnit_abbreviation(String unit_abbreviation) {
		this.unit_abbreviation = unit_abbreviation;
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
	

	@Override
	public String toString() {
		return "Unit [unit_id=" + unit_id + ", unit_name=" + unit_name + ", unit_abbreviation=" + unit_abbreviation
				+ ", unit_created_time=" + unit_created_time + ", unit_updated_time=" + unit_updated_time + "]";
	}
	
	public Unit(int unit_id, String unit_name, String unit_abbreviation, Timestamp unit_created_time,
			Timestamp unit_updated_time) {
		super();
		this.unit_id = unit_id;
		this.unit_name = unit_name;
		this.unit_abbreviation = unit_abbreviation;
		this.unit_created_time = unit_created_time;
		this.unit_updated_time = unit_updated_time;
	}
	
	public Unit() {
		
	}
	
	
	
	

}
