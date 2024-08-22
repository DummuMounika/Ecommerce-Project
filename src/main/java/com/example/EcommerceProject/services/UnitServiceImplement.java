package com.example.EcommerceProject.services;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EcommerceProject.exceptions.NotFoundException;
import com.example.EcommerceProject.model.Unit;
import com.example.EcommerceProject.modelEntity.UnitEntity;
import com.example.EcommerceProject.repository.UnitRepository;

@Service
public class UnitServiceImplement  implements UnitService{
    
	@Autowired
	private UnitRepository unitRepository;
	
	//convertMethods
	private Unit convertUnitEntityToUnit(UnitEntity unitEntity) {
		return new Unit(unitEntity.getUnit_id(),unitEntity.getUnit_name(),unitEntity.getUnit_abbreviation(),
				unitEntity.getUnit_created_time(),unitEntity.getUnit_updated_time());
	}
	
	private List<Unit> convertUnitEntityListToUnitList(List<UnitEntity> unitEntityList){
		List<Unit> unitList = new ArrayList<Unit>();
		for(UnitEntity unitEntity: unitEntityList) {
			Unit unit = convertUnitEntityToUnit(unitEntity);
            unitList.add(unit);
		}
		return unitList;
	}
	
	private UnitEntity convertUnitToUnitEntity(Unit unit) {
		return new UnitEntity(unit.getUnit_name(), unit.getUnit_abbreviation());
	}
	
	//CRUD Methods
	@Override
	public List<Unit> getAllUnits() {
		List<UnitEntity> unitEntityList = unitRepository.findAll();
		List<Unit> unitList = convertUnitEntityListToUnitList(unitEntityList);
		return unitList;
	}

	@Override
	public boolean createUnit(Unit unit) {
		UnitEntity unitEntity = convertUnitToUnitEntity(unit);
		Timestamp curentTimestamp = unitRepository.findCurrentTimeStamp();
		unitEntity.setUnit_created_time(curentTimestamp);
		unitEntity.setUnit_updated_time(curentTimestamp);
		UnitEntity entity = unitRepository.save(unitEntity);	
		if (entity != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String deleteUnit(int unitId) {
		Optional<UnitEntity> removeUnitEntity = unitRepository.findById(unitId);
			if(removeUnitEntity.isPresent()) {
				unitRepository.deleteById(unitId);
			return "The unit " + unitId + " deleted successfully" ;
		}else {
			throw new NotFoundException(unitId +" unit Id not Found");
		}
	}

	@Override
	public Unit updateUnit(Unit unit, int unitId) {
		UnitEntity existingUnitEntity = unitRepository.findById(unitId)
				.orElseThrow(() -> new NotFoundException(unitId +" unit Id not Found"));
		existingUnitEntity.setUnit_name(unit.getUnit_name());
		existingUnitEntity.setUnit_abbreviation(unit.getUnit_abbreviation());
		existingUnitEntity.setUnit_updated_time(unitRepository.findCurrentTimeStamp());
		UnitEntity savedUnitEntity = unitRepository.save(existingUnitEntity);
		return convertUnitEntityToUnit(savedUnitEntity);
		}

	@Override
	public Unit getUnit(int unitId) {
		UnitEntity singleUnitEntity = unitRepository.findById(unitId)
				.orElseThrow(() ->  new NotFoundException(unitId +" unit Id not Found"));
		Unit singleUnit = convertUnitEntityToUnit(singleUnitEntity);
	    return singleUnit;
	}


	@Override
	public int findUnitId(String unit_Name) {
		UnitEntity unitEntity = unitRepository.findByUnitName(unit_Name);
		if (unitEntity == null) {
			throw new NotFoundException(unit_Name +" unit name not Found");		
		}
		return unitEntity.getUnit_id();		
	}

	@Override
	public Unit findUnitByFilters(String unit_Name, String unit_abbreviation) {
		
		  UnitEntity singleUnitEntity = unitRepository.findByUnitNameAndUnitAbbreviation(unit_Name,
		   unit_abbreviation); 
		  if (singleUnitEntity == null) { 
			  throw new NotFoundException("The unit name "+unit_Name +" and abbreviation name "+unit_abbreviation+ " not Found"); 
			  } 
		  return convertUnitEntityToUnit(singleUnitEntity);	
	}
	
	

	

}
