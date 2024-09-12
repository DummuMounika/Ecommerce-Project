package com.example.ecommerce.project.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.ecommerce.project.exceptions.ConflictException;
import com.example.ecommerce.project.exceptions.NotFoundException;
import com.example.ecommerce.project.model.Unit;
import com.example.ecommerce.project.model.entity.UnitEntity;
import com.example.ecommerce.project.repository.UnitRepository;

@Service
public class UnitServiceImplement  implements UnitService{

	private UnitRepository unitRepository;
	
	Logger logger = Logger.getLogger(getClass().getName());
	private static final String MSG = " unit Id not Found";
	
	@Autowired
	public UnitServiceImplement(UnitRepository unitRepository) {
		super();
		this.unitRepository = unitRepository;
	}
	
	

	//convertMethods

	/**
	 * This method converts a {@link UnitEntity} object to a {@link Unit} object.
	 *
	 * @param productEntity {@link UnitEntity}
	 * @return product {@link Unit}
	 */
	private Unit convertUnitEntityToUnit(UnitEntity unitEntity) {
		return new Unit(unitEntity.getUnitId(),unitEntity.getUnitName(),unitEntity.getUnitAbbreviation(),
				unitEntity.getUnitCreatedTime(),unitEntity.getUnitUpdatedTime());
	}

	/**
	 * This method converts a list of {@link UnitEntity} objects to a list of {@link Unit} objects.
	 *
	 * @param {@link List} of {@link UnitEntity}.
	 * @return A {@link List} of {@link Unit}.
	 */
	private List<Unit> convertUnitEntityListToUnitList(List<UnitEntity> unitEntityList){
		List<Unit> unitList = new ArrayList<>();
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
		return new UnitEntity(unit.getUnitName(), unit.getUnitAbbreviation());
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
		return convertUnitEntityListToUnitList(unitEntityList);
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
	    try {
	        // Convert Unit to UnitEntity
	        UnitEntity unitEntity = convertUnitToUnitEntity(unit);

	        // Fetch current timestamp
	        Timestamp currentTimestamp = unitRepository.findCurrentTimeStamp();
	        unitEntity.setUnitCreatedTime(currentTimestamp);
	        unitEntity.setUnitUpdatedTime(currentTimestamp);

	        // Save the entity
	        unitRepository.save(unitEntity);
	        return true;
	    } catch (Exception e) {
	        // Log the exception
	        logger.severe("Failed to create unit: " + e.getMessage());
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
			logger.info(">>>start>Deleting Unit name: " + removeUnitEntity.get().getUnitName());
			
			try {
				unitRepository.deleteById(unitId);
				logger.info(String.format(">end>>>Deleted Unit ID: %d", unitId));
				return "The unit " + unitId + " deleted successfully" ;
			} catch (DataIntegrityViolationException  e) {
				throw new ConflictException(e.getMessage()); // Re-throwing the exception to preserve the original behavior
			}catch (Exception e) {
				throw new IllegalArgumentException(e.getMessage());
			}		
		}else {
			throw new NotFoundException(unitId + MSG);
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
			existingUnitEntity.get().setUnitName(unit.getUnitName());
			existingUnitEntity.get().setUnitAbbreviation(unit.getUnitAbbreviation());
			existingUnitEntity.get().setUnitUpdatedTime(unitRepository.findCurrentTimeStamp());
			UnitEntity savedUnitEntity = unitRepository.save(existingUnitEntity.get());
			return convertUnitEntityToUnit(savedUnitEntity);
		}else {
			throw new NotFoundException(unitId +MSG);
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
				.orElseThrow(() ->  new NotFoundException(unitId + MSG));
		return convertUnitEntityToUnit(singleUnitEntity);
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
		return unitEntity.getUnitId();		
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
