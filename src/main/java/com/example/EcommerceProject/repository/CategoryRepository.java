package com.example.EcommerceProject.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.EcommerceProject.modelEntity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
    @Query("SELECT categoryE FROM CategoryEntity categoryE WHERE categoryE.category_name = ?1")   
	CategoryEntity findByName(String categoryName);
}
