package com.example.ecommerce.project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ecommerce.project.model.entity.UnitEntity;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UnitRepositoryTest {

    @Autowired
    private UnitRepository unitRepository;

    //@Test
    @DisplayName("Test 1: Find Current Timestamp")
    @Order(1)
    void testFindCurrentTimeStamp() {
        Timestamp timestamp = unitRepository.findCurrentTimeStamp();
        assertThat(timestamp).isNotNull();
    }

    @Test
    @DisplayName("Test 2: Find by Unit Name")
    @Order(2)
    void testFindByUnitName() {
        UnitEntity unit = new UnitEntity();
        //unit.setUnitId(1);
        unit.setUnitName("Kilogram");
        unit.setUnitAbbreviation("kg");

        unitRepository.save(unit);

        UnitEntity foundUnit = unitRepository.findByUnitName("Kilogram");

        assertThat(foundUnit).isNotNull();
        assertThat(foundUnit.getUnitName()).isEqualTo("Kilogram");
    }

    @Test
    @DisplayName("Test 3: Find by Unit name - Not Found")
    @Order(3)
    void testFindByUnitName_NotFound() {
        UnitEntity foundUnit = unitRepository.findByUnitName("NonExistentUnit");
        
        assertThat(foundUnit).isNull();
    }

    @Test
    @DisplayName("Test 4: Find by Unit name and Unit abbreviation")
    @Order(4)
    void testFindByUnitNameAndUnitAbbreviation() {
        UnitEntity unit = new UnitEntity();
        unit.setUnitId(1);
        unit.setUnitName("Kilogram");
        unit.setUnitAbbreviation("kg");

        unitRepository.save(unit);

        UnitEntity foundUnit = unitRepository.findByUnitNameAndUnitAbbreviation("Kilogram", "kg");
        UnitEntity notFoundUnit = unitRepository.findByUnitNameAndUnitAbbreviation("Pounds", "pounds");
        assertThat(notFoundUnit).isNull();
        assertThat(foundUnit).isNotNull();
        assertThat(foundUnit.getUnitName()).isEqualTo("Kilogram");
        assertThat(foundUnit.getUnitAbbreviation()).isEqualTo("kg");
    }

    @Test
    @DisplayName("Test 5: Find by Unit name and Unit abbreviation-Not Found")
    @Order(5)
    void testFindByUnitNameAndUnitAbbreviation_NotFound() {
        UnitEntity foundUnit = unitRepository.findByUnitNameAndUnitAbbreviation("NonExistentUnit", "NE");
        assertThat(foundUnit).isNull(); 
    }
}

