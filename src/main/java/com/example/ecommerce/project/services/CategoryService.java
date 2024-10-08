package com.example.ecommerce.project.services;

import java.util.List;

import com.example.ecommerce.project.model.Category;

public interface CategoryService {
	
	public List<Category> getAllCategories();
	public int createCategory(Category category);
	public String deleteCategory(int categoryId);
	public Category updateCategory(Category category, int categoryId);
	public Category getSingleCategory(int categoryId);
	public Category findByCategoryName(String categoryName);
	
}
