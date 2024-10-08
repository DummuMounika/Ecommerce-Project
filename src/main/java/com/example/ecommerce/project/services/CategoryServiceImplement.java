package com.example.ecommerce.project.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.ecommerce.project.exceptions.NotFoundException;
import com.example.ecommerce.project.model.Category;
import com.example.ecommerce.project.model.Product;
import com.example.ecommerce.project.model.entity.CategoryEntity;
import com.example.ecommerce.project.model.entity.ProductEntity;
import com.example.ecommerce.project.repository.CategoryRepository;


@Service
public class CategoryServiceImplement implements CategoryService {


	private final CategoryRepository categoryRepository;
	@Lazy
	private final ProductService productService;
	
	Logger logger = Logger.getLogger(getClass().getName());
	private static final String MSG = "category Id not found";
	
	@Autowired
	public CategoryServiceImplement(CategoryRepository categoryRepository, @Lazy ProductService productService) {
		super();
		this.categoryRepository = categoryRepository;
		this.productService = productService;
	}

	//convertMethods
	private Category convertCategoryEntityToCategory(CategoryEntity categoryEntity) {
		List<ProductEntity> productEntityList = categoryEntity.getProductEntity();
		List<Product> productList = productService.convertProductEntityListToProductList(productEntityList);
		return new Category(categoryEntity.getCategoryId(),categoryEntity.getCategoryName(),productList,
				categoryEntity.getCategoryCreatedTime(),categoryEntity.getCategoryUpdatedTime());
	}

	private List<Category> convertCategoryEntityListToCategoryList(List<CategoryEntity> categoryEntityList){
		List<Category> categoryList = new ArrayList<>();
		for(CategoryEntity categoryEntity: categoryEntityList) {
			Category category = convertCategoryEntityToCategory(categoryEntity);
			categoryList.add(category);
		}
		return categoryList;
	}

	private CategoryEntity convertCategorytToCategoryEntity(Category category) {
		return new CategoryEntity(category.getCategoryName());
	}

	//CRUD Methods
	@Override
	public List<Category> getAllCategories() {
		List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
		if (!categoryEntityList.isEmpty()) {
	        int productSize = categoryEntityList.get(0).getProductEntity().size();
	        logger.info(String.format("Checking the product size: %d", productSize));
	    } else {
	        logger.info("No categories found or product list is empty.");
	    }
		return convertCategoryEntityListToCategoryList(categoryEntityList);
	}

	@Override
	public int createCategory(Category category) {
		Category existingCategory = findByCategoryName(category.getCategoryName());

		if (existingCategory == null) {
			CategoryEntity categoryEntity = convertCategorytToCategoryEntity(category);
			Timestamp currentTimestamp = categoryRepository.findCurrentTimeStamp();
			categoryEntity.setCategoryCreatedTime(currentTimestamp);
			categoryEntity.setCategoryUpdatedTime(currentTimestamp);
			CategoryEntity categoryEntityFromDatabase = categoryRepository.save(categoryEntity);
			return categoryEntityFromDatabase.getCategoryId();
		}else {
			return existingCategory.getCategoryId();
		}
	}

	@Override
	public String deleteCategory(int categoryId) {
		CategoryEntity removeCategoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException(MSG));
		categoryRepository.delete(removeCategoryEntity);
		return "The " + categoryId + " deleted successfully" ;
	}

	@Override
	public Category updateCategory(Category category, int categoryId) {
		CategoryEntity existingCategoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException(MSG));
		existingCategoryEntity.setCategoryName(category.getCategoryName());
		existingCategoryEntity.setCategoryUpdatedTime(categoryRepository.findCurrentTimeStamp());
		CategoryEntity savedCategoryEntity = categoryRepository.save(existingCategoryEntity);
		return convertCategoryEntityToCategory(savedCategoryEntity);
	}

	@Override
	public Category getSingleCategory(int categoryId) {
		CategoryEntity singleCategoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException(MSG));
		return convertCategoryEntityToCategory(singleCategoryEntity);
	}



	@Override
	public Category findByCategoryName(String categoryName) {
		CategoryEntity categoryEntity = categoryRepository.findByName(categoryName);
		if (categoryEntity != null) {
			return convertCategoryEntityToCategory(categoryEntity);
		}else {
			return null;
		}

	}


}
