package com.example.ecommerce.project.services;

import java.util.List;

import com.example.ecommerce.project.model.Unit;
import com.example.ecommerce.project.model.entity.UnitEntity;


public interface UnitService {
	
	public List<Unit> getAllUnits();
	public boolean createUnit(Unit unit);
	public String deleteUnit(int unitId);
	public Unit updateUnit(Unit unit, int unitId);
	public Unit getSingleUnit(int unitId);
	public int findUnitId(String unitName);
	public Unit findUnitByFilters(String unitName,String unitAbbreviation);
	public Unit convertUnitEntityToUnit(UnitEntity unitEntity);
}
