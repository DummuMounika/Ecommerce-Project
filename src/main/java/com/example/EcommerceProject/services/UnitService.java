package com.example.EcommerceProject.services;

import java.util.List;

import com.example.EcommerceProject.model.Unit;


public interface UnitService {
	
	public List<Unit> getAllUnits();
	public boolean createUnit(Unit unit);
	public String deleteUnit(int unit_Id);
	public Unit updateUnit(Unit unit, int unit_Id);
	public Unit getUnit(int unit_Id);
	public int findUnitId(String unit_Name);
	public Unit findUnitByFilters(String unit_Name,String unit_abbreviation);
}
