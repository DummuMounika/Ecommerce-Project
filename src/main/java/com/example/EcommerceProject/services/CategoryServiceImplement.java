package com.example.EcommerceProject.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.EcommerceProject.exceptions.NotFoundException;
import com.example.EcommerceProject.model.Category;
import com.example.EcommerceProject.model.Product;
import com.example.EcommerceProject.modelEntity.CategoryEntity;
import com.example.EcommerceProject.modelEntity.ProductEntity;
import com.example.EcommerceProject.repository.CategoryRepository;


@Service
public class CategoryServiceImplement implements CategoryService {
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
    @Lazy
    @Autowired
    private ProductService productService;


	//convertMethods
	private Category convertCategoryEntityToCategory(CategoryEntity categoryEntity) {
		List<ProductEntity> productEntityList = categoryEntity.getProductEntity();
		List<Product> productList = productService.convertProductEntityListToProductList(productEntityList);
		return new Category(categoryEntity.getCategory_id(),categoryEntity.getCategory_name(),productList,
				categoryEntity.getCategory_created_time(),categoryEntity.getCategory_updated_time());
	}
	
	private List<Category> convertCategoryEntityListToCategoryList(List<CategoryEntity> categoryEntityList){
		List<Category> categoryList = new ArrayList<Category>();
		for(CategoryEntity categoryEntity: categoryEntityList) {
			Category category = convertCategoryEntityToCategory(categoryEntity);
			categoryList.add(category);
		}
		return categoryList;
	}
	
	private CategoryEntity convertCategorytToCategoryEntity(Category category) {
	      return new CategoryEntity(category.getCategory_name());
	}
    
	//CRUD Methods
	@Override
	public List<Category> getAllCategories() {
		List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
	    System.out.println("Cheking the product size: " + categoryEntityList.get(0).getProductEntity().size()); 
	    List<Category> categoryList = convertCategoryEntityListToCategoryList(categoryEntityList);
	    return categoryList;
	}

	@Override
	public int createCategory(Category category) {
		Category existingCategory = findByCategoryName(category.getCategory_name());
		
		if (existingCategory == null) {
			CategoryEntity categoryEntity = convertCategorytToCategoryEntity(category);
			Timestamp currentTimestamp = categoryRepository.findCurrentTimeStamp();
			categoryEntity.setCategory_created_time(currentTimestamp);
			categoryEntity.setCategory_updated_time(currentTimestamp);
			CategoryEntity categoryEntityFromDatabase = categoryRepository.save(categoryEntity);
			return categoryEntityFromDatabase.getCategory_id();
		}else {
			return existingCategory.getCategory_id();
		}
	}

	@Override
	public String deleteCategory(int categoryId) {
		CategoryEntity removeCategoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException("category Id not found"));
		categoryRepository.delete(removeCategoryEntity);
		return "The " + categoryId + " deleted successfully" ;
	}

	@Override
	public Category updateCategory(Category category, int categoryId) {
		CategoryEntity existingCategoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException("category Id not found"));
		existingCategoryEntity.setCategory_name(category.getCategory_name());
		existingCategoryEntity.setCategory_updated_time(categoryRepository.findCurrentTimeStamp());
		CategoryEntity savedCategoryEntity = categoryRepository.save(existingCategoryEntity);
		return convertCategoryEntityToCategory(savedCategoryEntity);
	}

	@Override
	public Category getSingleCategory(int categoryId) {
		CategoryEntity singleCategoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException("category Id not found"));
	    Category singleCategory = convertCategoryEntityToCategory(singleCategoryEntity);
	    return singleCategory;
	}



	@Override
	public Category findByCategoryName(String categoryName) {
		CategoryEntity categoryEntity = categoryRepository.findByName(categoryName);
		if (categoryEntity != null) {
			Category category = convertCategoryEntityToCategory(categoryEntity);
			return category;
		}else {
			return null;
		}
		
	}
	

}
