package com.example.ecommerce.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ecommerce.project.model.Category;
import com.example.ecommerce.project.services.CategoryService;

@RestController
public class CategoryController {
	
	
	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/public/categories")
	public ResponseEntity<List<Category>> getAllcategoryDetails() {
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/public/categories/{categoryId}")
	@Cacheable(value = "Category" , key = "#id")
	public ResponseEntity<Category> getSingleCategoryDetail(@PathVariable Integer categoryId) {
		Category category = categoryService.getSingleCategory(categoryId);
		    return new ResponseEntity<>(category, HttpStatus.OK);
	}
	

	@PostMapping("/public/categories")
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		int categoryId =  categoryService.createCategory(category);
		return new ResponseEntity<>("New category added successfully with id:" + categoryId ,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/public/categories/{categoryId}")
	//@CacheEvict(cacheNames = "Customer" , key = "#id" , beforeInvocation = true)
	public ResponseEntity<String> deleteCategoryDetail(@PathVariable Integer categoryId) {
		try {
			String categoryStatus = categoryService.deleteCategory(categoryId);
		    return new ResponseEntity<>(categoryStatus, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
		
	}
	
	@PutMapping("/public/categories/{categorytId}")
	@CachePut(cacheNames = "Customer" , key = "#id")
	public ResponseEntity<String> updateCategoryDetail(@RequestBody Category product, @PathVariable Integer categorytId) {
		try {
			categoryService.updateCategory(product, categorytId);
			return new ResponseEntity<>("Category Detail with: " + categorytId + " updated successfully",HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
		
	}

	
	

}
