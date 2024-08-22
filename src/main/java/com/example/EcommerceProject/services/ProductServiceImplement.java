package com.example.EcommerceProject.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.EcommerceProject.exceptions.NotFoundException;
import com.example.EcommerceProject.model.Category;
import com.example.EcommerceProject.model.Product;
import com.example.EcommerceProject.model.Unit;
import com.example.EcommerceProject.modelEntity.CategoryEntity;
import com.example.EcommerceProject.modelEntity.ProductEntity;
import com.example.EcommerceProject.repository.CategoryRepository;
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
	private Product convertProductEntityToProduct(ProductEntity productEntity) {
		return new Product(productEntity.getProductId(),productEntity.getProduct_name(),productEntity.getProduct_description(),
						productEntity.getProduct_price(),productEntity.getProduct_stock_quantity(),productEntity.getCategory_id(),
						productEntity.getCategoryEntity().getCategory_name(),
						productEntity.getUnit_id(),
						productEntity.getUnitEntity().getUnit_name(),
						productEntity.getProduct_image_url(),productEntity.getProduct_created_time(),
						productEntity.getProduct_updated_time());
	}
	
	@Override
	public List<Product> convertProductEntityListToProductList(List<ProductEntity> productEntityList){
		List<Product> productList = new ArrayList<Product>();
		for(ProductEntity productEntity: productEntityList) {
			Product product = convertProductEntityToProduct(productEntity);
			productList.add(product);
		}
		return productList;
	}
	
	private ProductEntity convertProductToProductEntity(Product product) {
	      return new ProductEntity(product.getProduct_name(),product.getProduct_description(),
	    		  product.getProduct_price(), product.getProduct_stock_quantity(),
	    		  product.getCategory_id(),
	    		  product.getUnit_id(),product.getProduct_image_url());
	}
	
	
	//CRUD Methods
	@Override
	public List<Product> getAllProducts() {
		List<ProductEntity> productEntityList = productRepository.findAll();
		List<Product> productList = convertProductEntityListToProductList(productEntityList);
		return productList;	
	}

	
	/**
	 * 
	 * @return {@link Boolean}
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
	
	private ProductEntity addProduct(Product product) {
		//Get categoryname from product
		int categoryId = categoryService.createCategory(new Category(product.getCategory_name()));
		int unitId = unitService.findUnitId(product.getUnit_name());
	
		ProductEntity productEntity = convertProductToProductEntity(product);
		productEntity.setUnit_id(unitId);
		productEntity.setCategory_id(categoryId);
		return productEntity;
		
	}
	
	@Override
	public Product updateProduct(Product product, int productId) {
		Optional<ProductEntity> existingProductEntity = productRepository.findById(productId);
				//.orElseThrow(() -> new NotFoundException("product Id not found"));
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
	
	@Override
	public String deleteProduct(int product_Id) {
		Optional<ProductEntity> removeProductEntity = productRepository.findById(product_Id);
		if(removeProductEntity.isEmpty()) {
			throw new NotFoundException("The product " +product_Id + " not found.");
		}else {
			// Logging to confirm deletion process is reached
	        System.out.println("Deleting Product ID: " + product_Id);
	        productRepository.deleteById(product_Id);
	        System.out.println("Deleted Product ID: " + product_Id);
	        return "The product with ID " + product_Id + " deleted successfully";		}
	}

	

	@Override
	public Product getSingleProduct(int productId) {
		ProductEntity singleProductEntity = productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundException("product Id not found"));
	    Product singleProduct = convertProductEntityToProduct(singleProductEntity);
		return singleProduct;
	}
	
	
	public Product findProductId(String productName) {
		ProductEntity productEntity = productRepository.findByproductName(productName);
	    if ( productEntity == null) {
	    	throw new NotFoundException("The product name "+productName+"  not found");
	    }
		return convertProductEntityToProduct(productEntity);
	}

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
