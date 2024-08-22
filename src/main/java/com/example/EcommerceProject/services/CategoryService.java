package com.example.EcommerceProject.services;

import java.util.List;

import com.example.EcommerceProject.model.Category;

public interface CategoryService {
	
	public List<Category> getAllCategories();
	public int createCategory(Category category);
	public String deleteCategory(int categoryId);
	public Category updateCategory(Category category, int category_Id);
	public Category getSingleCategory(int category_Id);
	public Category findByCategoryName(String categoryName);
	
}
