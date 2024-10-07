package com.example.ecommerce.project.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.ecommerce.project.exceptions.NotFoundException;
import com.example.ecommerce.project.model.Unit;
import com.example.ecommerce.project.model.entity.UnitEntity;
import com.example.ecommerce.project.repository.UnitRepository;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@ExtendWith(MockitoExtension.class)
class UnitServiceImplementTest {

	@Mock
	private UnitRepository unitRepository;
	
	@InjectMocks
	private UnitServiceImplement unitService;

	private UnitEntity unit;
	private List<UnitEntity> unitEntityList;

	@BeforeEach
	public void setup() {
	
		unitEntityList = new ArrayList<>();
		unit = new UnitEntity("gram", "gm");
		UnitEntity unit2 = new UnitEntity("kilogram", "kg");
		UnitEntity unit3 = new UnitEntity("ouches", "ouches");
		unitEntityList.add(unit);
		unitEntityList.add(unit2);
		unitEntityList.add(unit3);
	}

	@Test
	void testGetAllUnits() {

		
		when(unitRepository.findAll()).thenReturn(unitEntityList);
		
		List<Unit> unitList = unitService.getAllUnits();
		assertNotNull(unitList);
		assertEquals(3, unitList.size());

	}

	@Test
	void testCreateUnit() {
		Unit unit = new Unit(1, "Gram", "gm", null, null);
		UnitEntity entityToSave = new UnitEntity("Gram", "gm");
		when(unitRepository.save(any(UnitEntity.class))).thenReturn(entityToSave);
		boolean result = unitService.createUnit(unit);

        assertTrue(result);
        verify(unitRepository).save(any(UnitEntity.class));
		

	}

	@Test
	void testDeleteUnit() {
		//case 1
		when(unitRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
	    assertThrowsExactly(NotFoundException.class, () -> unitService.deleteUnit(1));

		//case 2 
		when(unitRepository.findById(any(Integer.class))).thenReturn(Optional.of(unit));
        Mockito.doNothing().when(unitRepository).deleteById(any(Integer.class));
		String result = unitService.deleteUnit(1);
		assertEquals("The unit 1 deleted successfully", result);
		verify(unitRepository).deleteById(1);
	}

	@Test
	void testUpdateUnit() {
		//case 1
		Unit updatedUnit = new Unit(1, "Pound", "lb", null, null);
		when(unitRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
	    assertThrowsExactly(NotFoundException.class, () -> unitService.updateUnit(updatedUnit,1));

		//case 2
        when(unitRepository.findById(1)).thenReturn(Optional.of(unit));
        when(unitRepository.save(any(UnitEntity.class))).thenReturn(unit);
        Unit result = unitService.updateUnit(updatedUnit, 1);

        assertEquals("Pound", result.getUnitName());
        verify(unitRepository).save(any(UnitEntity.class));
	}

	@Test
	void testGetSingleUnit() {
		//case 1
		when(unitRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
	    assertThrowsExactly(NotFoundException.class, () -> unitService.getSingleUnit(1));
		//case 2
		when(unitRepository.findById(unit.getUnitId())).thenReturn(Optional.of(unit));
		Unit result = unitService.getSingleUnit(unit.getUnitId()); 
		assertNotNull(result);
		assertEquals(unit.getUnitName(), result.getUnitName());
	}


}
