package com.example.EcommerceProject.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.EcommerceProject.exceptions.ConflictException;
import com.example.EcommerceProject.exceptions.NotFoundException;
import com.example.EcommerceProject.model.Category;
import com.example.EcommerceProject.model.Product;
import com.example.EcommerceProject.modelEntity.ProductEntity;
import com.example.EcommerceProject.repository.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class ProductServiceImplement  implements ProductService{


	private ProductRepository productRepository;
	@Lazy
	private CategoryService  categoryService;	
	private UnitService unitService;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    public ProductServiceImplement(ProductRepository productRepository, CategoryService categoryService,
			UnitService unitService) {
		super();
		this.productRepository = productRepository;
		this.categoryService = categoryService;
		this.unitService = unitService;
    }


	//convertMethods

	/**
	 * This method converts a {@link ProductEntity} object to a {@link Product} object.
	 *
	 * @param productEntity {@link ProductEntity}
	 * @return product {@link Product}
	 */
	private Product convertProductEntityToProduct(ProductEntity productEntity) {
		return new Product(productEntity.getProductId(),productEntity.getProductName(),productEntity.getProductDescription(),
				productEntity.getProductPrice(),productEntity.getProductStockQuantity(),productEntity.getCategoryId(),
				productEntity.getCategoryEntity().getCategoryName(),
				productEntity.getUnitId(),
				productEntity.getUnitEntity().getUnitName(),
				productEntity.getProductImageUrl(),productEntity.getProductCreatedTime(),
				productEntity.getProductUpdatedTime());
	}

	/**
	 * This method converts a list of {@link ProductEntity} objects to a list of {@link Product} objects.
	 *
	 * @param {@link List} of {@link ProductEntity}.
	 * @return A {@link List} of {@link Product}.
	 */
	@Override
	public List<Product> convertProductEntityListToProductList(List<ProductEntity> productEntityList){
		List<Product> productList = new ArrayList<>();
		for(ProductEntity productEntity: productEntityList) {
			Product product = convertProductEntityToProduct(productEntity);
			productList.add(product);
		}
		return productList;
	}

	/**
	 * This method converts a {@link Product} object to a {@link ProductEntity} object.
	 *
	 * @param product {@link Product}
	 * @return productEntity {@link ProductEntity}
	 */
	private ProductEntity convertProductToProductEntity(Product product) {
		return new ProductEntity(product.getProductName(),product.getProductDescription(),
				product.getProductPrice(), product.getProductStockQuantity(),
				product.getCategoryId(),
				product.getUnitId(),product.getProductImageUrl());
	}
	
	
	   
	    private List<ProductEntity> findWithLimitAndOffset(int limit, int offset, String sortBy, String sortDir) {
	        String sql = "SELECT * FROM products ORDER BY " + sortBy + " " + sortDir + " LIMIT :limit OFFSET :offset";
	        Query query = entityManager.createNativeQuery(sql, ProductEntity.class);
	        query.setParameter("limit", limit);
	        query.setParameter("offset", offset);
	        return query.getResultList();
	    }



	//Below are the CRUD operations

	/**
	 * This function retrieves all products from the database.
	 *
	 * @return A {@link List} of {@link Product}
	 */
	@Override
	public List<Product> getAllProducts(int limit, int offset, String sortBy, String sortDirection) {
		List<ProductEntity> productEntityList;
		
		List<String> sortDirections = new ArrayList<>();
		sortDirections.add("ASC");
		sortDirections.add("DESC");
		if(!sortDirections.contains(sortDirection)) {
			throw new IllegalArgumentException("Invalid sort Directions.");
		}
		
	    if (limit > 0 && offset >= 0 && !sortBy.isEmpty() && !sortDirection.isEmpty()) {
	       productEntityList = findWithLimitAndOffset(limit, offset, sortBy, sortDirection);
	    	//findWithLimitAndOffset
	    } else {
	        productEntityList = productRepository.findAll();
	    }

	    if (productEntityList.isEmpty()) {
	        throw new NotFoundException("products not found");
	    }

	    return convertProductEntityListToProductList(productEntityList);
		}


	/**
	 * This function is part of the service layer and 
	 * handles the business logic for adding new products to your DB.
	 * 
	 * @param  product ({@link Product} model)
	 * @return Boolean
	 */
	@Override
	public boolean createProduct(Product product) {
		try {
	        // Retrieve current timestamp
	        Timestamp currentTimestamp = productRepository.findCurrentTimeStamp();

	        // Convert Product to ProductEntity and set timestamps
	        ProductEntity newProductEntity = addProduct(product);
	        newProductEntity.setProductCreatedTime(currentTimestamp);
	        newProductEntity.setProductUpdatedTime(currentTimestamp);

	        // Log the new product entity
	        logger.info("Creating product: " + newProductEntity.toString());

	        // Save the entity
	        productRepository.save(newProductEntity);
	        return true;
	    } catch (Exception e) {
	        // Log the exception
	        logger.severe("Failed to create product: " + e.getMessage());
	        return false;
	    }
	}

	/**
	 * This function converts a {@link Product} model to a {@link ProductEntity} and sets category 
	 * and unit IDs based on the product details.
	 *
	 * @param product ({@link Product} model)
	 * @return  {@link ProductEntity}
	 */
	private ProductEntity addProduct(Product product) {
		//Get categoryname from product
		int categoryId = categoryService.createCategory(new Category(product.getCategoryName()));
		int unitId = unitService.findUnitId(product.getUnitName());

		ProductEntity productEntity = convertProductToProductEntity(product);
		productEntity.setUnitId(unitId);
		productEntity.setCategoryId(categoryId);
		return productEntity;

	}

	/**
	 * This function updates an existing product in the database.
	 * 
	 * @param product ({@link Product} model) and productId
	 * @return {@link Product}
	 */
	@Override
	public Product updateProduct(Product product, int productId) {
		Optional<ProductEntity> existingProductEntity = productRepository.findById(productId);
		if(existingProductEntity.isPresent()) {
			ProductEntity productEntity = addProduct(product);
			productEntity.setProductId(existingProductEntity.get().getProductId());
			productEntity.setProductCreatedTime(existingProductEntity.get().getProductCreatedTime());
			productEntity.setProductUpdatedTime(productRepository.findCurrentTimeStamp());
			ProductEntity saveProductEntity = productRepository.save(productEntity);
			return convertProductEntityToProduct(saveProductEntity) ;

		}else {
			throw new NotFoundException("product Id not found");
		}

	}

	/**
	 * This function deletes a product from the database by its ID.
	 * 
	 * @param productId
	 * @return String (confirmation message)
	 */
	@Override
	public String deleteProduct(int productId) {
		Optional<ProductEntity> removeProductEntity = productRepository.findById(productId);
		if(removeProductEntity.isPresent()) {
			// Logging to confirm deletion process is reached
			logger.info(">>>start>Deleting Product name: " + removeProductEntity.get().getProductName());
			try {
				productRepository.deleteById(productId);
			} catch (DataIntegrityViolationException  e) {
				throw new ConflictException(e.getMessage()); // Re-throwing the exception to preserve the original behavior
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			logger.info(">end>>>Deleted Product ID: " + productId);
			return "The product with ID " + productId + " deleted successfully";		
		}else {
			throw new NotFoundException("The product " +productId + " not found.");
		}
	}

	/**
	 * This function retrieves a single product from the database by its ID.
	 * 
	 * @param productId
	 * @return {@link Product}
	 */
	@Override
	public Product getSingleProduct(int productId) {
		ProductEntity singleProductEntity = productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundException("product Id not found"));
		return convertProductEntityToProduct(singleProductEntity);
	}

	/**
	 * This function retrieves multiple products from the database by their IDs.
	 * 
	 * @param product Ids
	 * @return {@link HashMap} where key is product Id and product {@link Product} model.
	 */
	@Override
	public HashMap<Integer, Product> getMultipleProducts(List<Integer> productIds) {
		List<ProductEntity> productEntities = productRepository.findAllById(productIds);
		HashMap<Integer, Product> productMap = new HashMap<>();
		for(ProductEntity productEntity: productEntities) {		    
			Product product = convertProductEntityToProduct(productEntity);   
			productMap.put(productEntity.getProductId(), product);
		}

		return productMap;
	}









}
