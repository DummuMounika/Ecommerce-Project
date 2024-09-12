package com.example.ecommerce.project.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.project.exceptions.NotFoundException;
import com.example.ecommerce.project.model.Unit;
import com.example.ecommerce.project.services.UnitService;

@RestController
public class UnitController {

	
	private UnitService unitService;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public UnitController(UnitService unitService) {
		super();
		this.unitService = unitService;
	}

	/**
	 * This function help us to retrieve Units
	 * 
	 * @return {@link List} of Unitt {@link Unit}
	 */
	@GetMapping("/public/units")
	public ResponseEntity<List<Unit>> getAllUnitDetails() {
		List<Unit> units = unitService.getAllUnits();
		return new ResponseEntity<>(units, HttpStatus.OK);
	}

	/**
	 * This function help us to retrieve unit
	 * 
	 * @return Unit {@link Unit}
	 */
	@GetMapping("/public/units/{unitId}")
	public ResponseEntity<Unit> getSingleUnitDetail(@PathVariable Integer unitId) {
		Unit unit = unitService.getSingleUnit(unitId);
		return new ResponseEntity<>(unit, HttpStatus.OK);
	}

	/**
	 * This function help us to get unit by filters
	 * 
	 * @param unit name and unit abbreviation
	 * @return {@link Unit}
	 */
	@GetMapping("/public/unitByFilters")
	public ResponseEntity<Unit> getSingleUnitByFilters(
			@RequestParam(required = true,value="unitname",defaultValue = "monu") String unitName,
			@RequestParam(required = true,value="unitabbreviation",defaultValue="dummu") String unitAbbreviation) {
		try {
			Unit unit = unitService.findUnitByFilters(unitName, unitAbbreviation);
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This function help us to get unit name
	 * 
	 * @param unit Id
	 * @return Integer
	 */
	@GetMapping("/public/unitsByName/{unitName}")
	public ResponseEntity<Integer> getUnitName(@PathVariable String unitName) {	
		try {
			int unitId = unitService.findUnitId(unitName);
			return new ResponseEntity<>(unitId, HttpStatus.OK);	
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This function help us to create units
	 * 
	 * @param {@link Unit}
	 * @return String (confirmation message)
	 */
	@PostMapping("/public/units")
	public ResponseEntity<String> createUnit(@RequestBody Unit unit) {
		boolean isCreated = unitService.createUnit(unit);
		if (isCreated) {
			return new ResponseEntity<>("New unit added successfully",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Failed to add new unit", HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * This function help us to delete unit
	 * 
	 * @param unit Id
	 * @return String (confirmation message)
	 */
	@DeleteMapping("/public/units/{unitId}")
	public ResponseEntity<String> deleteUnitDetail(@PathVariable Integer unitId) {
		try {
			String unitStatus = unitService.deleteUnit(unitId);
			return new ResponseEntity<>(unitStatus, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * This function help us to update units
	 * @param unit Id
	 * @return String (confirmation message)
	 */
	@PutMapping("/public/units/{unitId}")
	public ResponseEntity<String> updateUnitDetail(@RequestBody Unit unit, @PathVariable Integer unitId) {
		try {
			unitService.updateUnit(unit, unitId);
			return new ResponseEntity<>("Unit Detail with: " + unitId + " updated successfully",HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
