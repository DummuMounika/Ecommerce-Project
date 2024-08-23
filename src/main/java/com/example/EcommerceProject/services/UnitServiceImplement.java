package com.example.EcommerceProject.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.EcommerceProject.exceptions.ConflictException;
import com.example.EcommerceProject.exceptions.NotFoundException;
import com.example.EcommerceProject.model.Unit;
import com.example.EcommerceProject.modelEntity.UnitEntity;
import com.example.EcommerceProject.repository.UnitRepository;

@Service
public class UnitServiceImplement  implements UnitService{

	@Autowired
	private UnitRepository unitRepository;

	//convertMethods

	/**
	 * This method converts a {@link UnitEntity} object to a {@link Unit} object.
	 *
	 * @param productEntity {@link UnitEntity}
	 * @return product {@link Unit}
	 */
	private Unit convertUnitEntityToUnit(UnitEntity unitEntity) {
		return new Unit(unitEntity.getUnit_id(),unitEntity.getUnit_name(),unitEntity.getUnit_abbreviation(),
				unitEntity.getUnit_created_time(),unitEntity.getUnit_updated_time());
	}

	/**
	 * This method converts a list of {@link UnitEntity} objects to a list of {@link Unit} objects.
	 *
	 * @param {@link List} of {@link UnitEntity}.
	 * @return A {@link List} of {@link Unit}.
	 */
	private List<Unit> convertUnitEntityListToUnitList(List<UnitEntity> unitEntityList){
		List<Unit> unitList = new ArrayList<Unit>();
		for(UnitEntity unitEntity: unitEntityList) {
			Unit unit = convertUnitEntityToUnit(unitEntity);
			unitList.add(unit);
		}
		return unitList;
	}

	/**
	 * This method converts a {@link Unit} object to a {@link UnittEntity} object.
	 *
	 * @param unit {@link Unit}
	 * @return unitEntity {@link UnitEntity}
	 */
	private UnitEntity convertUnitToUnitEntity(Unit unit) {
		return new UnitEntity(unit.getUnit_name(), unit.getUnit_abbreviation());
	}

	//Below are the CRUD operations

	/**
	 * This function retrieves all units from the database.
	 *
	 * @return A {@link List} of {@link Unit}
	 */
	@Override
	public List<Unit> getAllUnits() {
		List<UnitEntity> unitEntityList = unitRepository.findAll();
		List<Unit> unitList = convertUnitEntityListToUnitList(unitEntityList);
		return unitList;
	}

	/**
	 * This function is part of the service layer and 
	 * handles the business logic for adding new units to your DB.
	 * 
	 * @param  unit ({@link Unit} model)
	 * @return Boolean
	 */
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

	/**
	 * This function deletes a product from the database by its ID.
	 * 
	 * @param unitId
	 * @return String (confirmation message)
	 */
	@Override
	public String deleteUnit(int unitId) {
		Optional<UnitEntity> removeUnitEntity = unitRepository.findById(unitId);
		if(removeUnitEntity.isPresent()) {
			// Logging to confirm deletion process is reached
			System.out.println(">>>start>Deleting Unit name: " + removeUnitEntity.get().getUnit_name());
			try {
				unitRepository.deleteById(unitId);
			} catch (DataIntegrityViolationException  e) {
				throw new ConflictException(e.getMessage()); // Re-throwing the exception to preserve the original behavior
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			System.out.println(">end>>>Deleted Unit ID: " + unitId);
			return "The unit " + unitId + " deleted successfully" ;
		}else {
			throw new NotFoundException(unitId +" unit Id not Found");
		}
	}

	/**
	 * This function updates an existing unit in the database.
	 * 
	 * @param unit ({@link Unit} model) and unitId
	 * @return {@link Unit}
	 */
	@Override
	public Unit updateUnit(Unit unit, int unitId) {
		Optional<UnitEntity> existingUnitEntity = unitRepository.findById(unitId);
		if (existingUnitEntity.isPresent()) {
			existingUnitEntity.get().setUnit_name(unit.getUnit_name());
			existingUnitEntity.get().setUnit_abbreviation(unit.getUnit_abbreviation());
			existingUnitEntity.get().setUnit_updated_time(unitRepository.findCurrentTimeStamp());
			UnitEntity savedUnitEntity = unitRepository.save(existingUnitEntity.get());
			return convertUnitEntityToUnit(savedUnitEntity);
		}else {
			throw new NotFoundException(unitId +" unit Id not Found");
		}	
	}


	/**
	 * This function retrieves a single unit from the database by its ID.
	 * 
	 * @param unitId
	 * @return {@link Unit}
	 */
	@Override
	public Unit getSingleUnit(int unitId) {
		UnitEntity singleUnitEntity = unitRepository.findById(unitId)
				.orElseThrow(() ->  new NotFoundException(unitId +" unit Id not Found"));
		Unit singleUnit = convertUnitEntityToUnit(singleUnitEntity);
		return singleUnit;
	}

	/**
	 * This function retrieves a unit id from the database by its unit name.
	 * 
	 * @param unitName
	 * @return {@link Integer}
	 */
	@Override
	public int findUnitId(String unitName) {
		UnitEntity unitEntity = unitRepository.findByUnitName(unitName);
		if (unitEntity == null) {
			throw new NotFoundException(unitName +" unit name not Found");		
		}
		return unitEntity.getUnit_id();		
	}

	/**
	 * This function retrieve a unit from the database by its name and abbreviation.
	 * 
	 * @param unitName and unitAbbreviation
	 * @return {@link Unit}
	 */
	@Override
	public Unit findUnitByFilters(String unitName, String unitAbbreviation) {

		UnitEntity singleUnitEntity = unitRepository.findByUnitNameAndUnitAbbreviation(unitName,
				unitAbbreviation); 
		if (singleUnitEntity == null) { 
			throw new NotFoundException("The unit name "+unitName +" and abbreviation name "+unitAbbreviation+ " not Found"); 
		} 
		return convertUnitEntityToUnit(singleUnitEntity);	
	}





}
