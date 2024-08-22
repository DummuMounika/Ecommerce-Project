package com.example.EcommerceProject.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.EcommerceProject.modelEntity.CustomerEntity;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();


	CustomerEntity findBycustomerEmail(String customerEmail);
	

}
