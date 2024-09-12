package com.example.ecommerce.project.repository;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ecommerce.project.model.entity.CustomerEntity;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();


	public Optional<CustomerEntity> findBycustomerEmail(String customerEmail);
	

}
