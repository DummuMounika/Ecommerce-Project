package com.example.ecommerce.project.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ecommerce.project.model.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
    @Query("SELECT categoryE FROM CategoryEntity categoryE WHERE categoryE.categoryName = ?1")   
	CategoryEntity findByName(String categoryName);
}
