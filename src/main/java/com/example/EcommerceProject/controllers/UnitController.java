package com.example.EcommerceProject.controllers;

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import com.example.EcommerceProject.model.Unit;
import com.example.EcommerceProject.services.UnitService;

@RestController
public class UnitController {
	
	@Autowired
	private UnitService unitService;
	
	@GetMapping("/public/units")
	public ResponseEntity<List<Unit>> getAllUnitDetails() {
		List<Unit> units = unitService.getAllUnits();
		return new ResponseEntity<>(units, HttpStatus.OK);
	}
	
	@GetMapping("/public/units/{unit_Id}")
	public ResponseEntity<Unit> getSingleUnitDetail(@PathVariable Integer unit_Id) {
		Unit unit = unitService.getUnit(unit_Id);
		    return new ResponseEntity<>(unit, HttpStatus.OK);
	}
	
	@GetMapping("/public/unitByFilters")
	public ResponseEntity<Unit> getSingleUnitByFilters(
			@RequestParam(required = true,value="unitname",defaultValue = "monu") String unit_name,
			@RequestParam(required = true,value="unitabbreviation",defaultValue="dummu") String unit_abbreviation) {
		System.out.println("Checking the req parameter: "+ unit_name +" " + unit_abbreviation);
		Unit unit = unitService.findUnitByFilters(unit_name, unit_abbreviation);
		return new ResponseEntity<>(unit, HttpStatus.OK);
	}
	
	@GetMapping("/public/unitsByName/{unit_name}")
	public ResponseEntity<Integer> getUnitName(@PathVariable String unit_name) {
		int unitId = unitService.findUnitId(unit_name);
		return new ResponseEntity<>(unitId, HttpStatus.OK);	
	}
	

	@PostMapping("/public/units")
	public ResponseEntity<String> createUnit(@RequestBody Unit unit) {
		boolean isCreated = unitService.createUnit(unit);
		if (isCreated) {
			return new ResponseEntity<>("New unit added successfully",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Failed to add new unit", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/public/units/{unit_Id}")
	public ResponseEntity<String> deleteUnitDetail(@PathVariable Integer unit_Id) {
		try {
			String unitStatus = unitService.deleteUnit(unit_Id);
		    return new ResponseEntity<>(unitStatus, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
		
	}
	
	@PutMapping("/public/units/{unit_Id}")
	public ResponseEntity<String> updateUnitDetail(@RequestBody Unit unit, @PathVariable Integer unit_Id) {
		try {
			Unit savedUnit = unitService.updateUnit(unit, unit_Id);
			return new ResponseEntity<>("Unit Detail with: " + unit_Id + " updated successfully",HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
		
	}

}
