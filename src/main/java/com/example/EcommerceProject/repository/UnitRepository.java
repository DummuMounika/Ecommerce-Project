package com.example.EcommerceProject.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.EcommerceProject.modelEntity.UnitEntity;

public interface UnitRepository extends JpaRepository<UnitEntity, Integer>{
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	//@Query("SELECT ue From UnitEntity ue WHERE ue.unit_name = ?1")
	UnitEntity findByUnitName(String unit_Name);
	
	// @Query("SELECT ue From UnitEntity ue WHERE ue.unitName = ?1 AND ue.unitAbbreviation = ?2")
	 UnitEntity findByUnitNameAndUnitAbbreviation(String unitName, String unitAbbreviation);
}
