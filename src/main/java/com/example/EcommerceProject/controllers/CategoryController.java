package com.example.EcommerceProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.EcommerceProject.model.Category;
import com.example.EcommerceProject.services.CategoryService;

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
	
	@GetMapping("/public/categories/{category_Id}")
	public ResponseEntity<Category> getSingleCategoryDetail(@PathVariable Integer category_Id) {
		Category category = categoryService.getSingleCategory(category_Id);
		    return new ResponseEntity<>(category, HttpStatus.OK);
	}
	

	@PostMapping("/public/categories")
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		int categoryId =  categoryService.createCategory(category);
		return new ResponseEntity<>("New category added successfully with id:" + categoryId ,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/public/categories/{category_Id}")
	public ResponseEntity<String> deleteCategoryDetail(@PathVariable Integer category_Id) {
		try {
			String categoryStatus = categoryService.deleteCategory(category_Id);
		    return new ResponseEntity<>(categoryStatus, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
		
	}
	
	@PutMapping("/public/categories/{categoryt_Id}")
	public ResponseEntity<String> updateCategoryDetail(@RequestBody Category product, @PathVariable Integer categoryt_Id) {
		try {
			Category savedProduct = categoryService.updateCategory(product, categoryt_Id);
			return new ResponseEntity<>("Category Detail with: " + categoryt_Id + " updated successfully",HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
		
	}

	
	

}
