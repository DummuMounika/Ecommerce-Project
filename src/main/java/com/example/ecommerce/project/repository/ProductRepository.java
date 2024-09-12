package com.example.ecommerce.project.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce.project.model.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	@Query(value="select current_timestamp",nativeQuery=true)
	Timestamp findCurrentTimeStamp();
	
	@Query("SELECT pe From ProductEntity pe WHERE pe.productName = ?1")
    ProductEntity findByproductName(String productName);
	
	@Query(value = "SELECT * FROM products ORDER BY :sortDir LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<ProductEntity> findWithLimitAndOffset(@Param("limit") int limit, 
		                                        @Param("offset") int offset,
		                                        //@Param("sortBy") int sortBy, 
		                                        @Param("sortDir") String sortDir);
}	

