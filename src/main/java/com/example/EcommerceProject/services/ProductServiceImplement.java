package com.example.EcommerceProject.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

@Service
public class ProductServiceImplement  implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Lazy
	@Autowired
	private CategoryService  categoryService;

	@Autowired
	private UnitService unitService;



	//convertMethods

	/**
	 * This method converts a {@link ProductEntity} object to a {@link Product} object.
	 *
	 * @param productEntity {@link ProductEntity}
	 * @return product {@link Product}
	 */
	private Product convertProductEntityToProduct(ProductEntity productEntity) {
		return new Product(productEntity.getProductId(),productEntity.getProduct_name(),productEntity.getProduct_description(),
				productEntity.getProduct_price(),productEntity.getProduct_stock_quantity(),productEntity.getCategory_id(),
				productEntity.getCategoryEntity().getCategory_name(),
				productEntity.getUnit_id(),
				productEntity.getUnitEntity().getUnit_name(),
				productEntity.getProduct_image_url(),productEntity.getProduct_created_time(),
				productEntity.getProduct_updated_time());
	}

	/**
	 * This method converts a list of {@link ProductEntity} objects to a list of {@link Product} objects.
	 *
	 * @param {@link List} of {@link ProductEntity}.
	 * @return A {@link List} of {@link Product}.
	 */
	@Override
	public List<Product> convertProductEntityListToProductList(List<ProductEntity> productEntityList){
		List<Product> productList = new ArrayList<Product>();
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
		return new ProductEntity(product.getProduct_name(),product.getProduct_description(),
				product.getProduct_price(), product.getProduct_stock_quantity(),
				product.getCategory_id(),
				product.getUnit_id(),product.getProduct_image_url());
	}



	//Below are the CRUD operations

	/**
	 * This function retrieves all products from the database.
	 *
	 * @return A {@link List} of {@link Product}
	 */
	@Override
	public List<Product> getAllProducts() {
		List<ProductEntity> productEntityList = productRepository.findAll();
		List<Product> productList = convertProductEntityListToProductList(productEntityList);
		return productList;	
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

		Timestamp currentTimestamp = productRepository.findCurrentTimeStamp();
		ProductEntity newProductEntity = addProduct(product);	
		newProductEntity.setProduct_created_time(currentTimestamp);
		newProductEntity.setProduct_updated_time(currentTimestamp);
		System.out.println(newProductEntity.toString());
		newProductEntity = productRepository.save(newProductEntity);

		if (newProductEntity != null) {
			return true;
		}else {
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
		int categoryId = categoryService.createCategory(new Category(product.getCategory_name()));
		int unitId = unitService.findUnitId(product.getUnit_name());

		ProductEntity productEntity = convertProductToProductEntity(product);
		productEntity.setUnit_id(unitId);
		productEntity.setCategory_id(categoryId);
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
			productEntity.setProduct_created_time(existingProductEntity.get().getProduct_created_time());
			productEntity.setProduct_updated_time(productRepository.findCurrentTimeStamp());
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
	public String deleteProduct(int product_Id) {
		Optional<ProductEntity> removeProductEntity = productRepository.findById(product_Id);
		if(removeProductEntity.isPresent()) {
			// Logging to confirm deletion process is reached
			System.out.println(">>>start>Deleting Product name: " + removeProductEntity.get().getProduct_name());
			try {
				productRepository.deleteById(product_Id);
			} catch (DataIntegrityViolationException  e) {
				throw new ConflictException(e.getMessage()); // Re-throwing the exception to preserve the original behavior
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			System.out.println(">end>>>Deleted Product ID: " + product_Id);
			return "The product with ID " + product_Id + " deleted successfully";		
		}else {
			throw new NotFoundException("The product " +product_Id + " not found.");
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
		Product singleProduct = convertProductEntityToProduct(singleProductEntity);
		return singleProduct;
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
	};









}
