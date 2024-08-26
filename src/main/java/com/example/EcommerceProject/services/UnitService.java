package com.example.EcommerceProject.services;

import java.util.List;

import com.example.EcommerceProject.model.Unit;


public interface UnitService {
	
	public List<Unit> getAllUnits();
	public boolean createUnit(Unit unit);
	public String deleteUnit(int unitId);
	public Unit updateUnit(Unit unit, int unitId);
	public Unit getSingleUnit(int unitId);
	public int findUnitId(String unitName);
	public Unit findUnitByFilters(String unitName,String unitAbbreviation);
}
