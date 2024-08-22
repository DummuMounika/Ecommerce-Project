package com.example.EcommerceProject.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.EcommerceProject.modelEntity.AddToCartEntity;
import com.example.EcommerceProject.modelEntity.CategoryEntity;

public interface CartRepository extends JpaRepository<AddToCartEntity, Integer> {

	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	//@Query("SELECT addToCartE FROM AddToCartEntity addToCartE WHERE addToCartE.customer_id = ?1 and addToCartE.product_id = ?2")
	Optional<AddToCartEntity> findByCustomerIdAndProductId(int customerId, int productId);
	
	List<AddToCartEntity> findAllByCustomerId(int customerId);
	
		
}
