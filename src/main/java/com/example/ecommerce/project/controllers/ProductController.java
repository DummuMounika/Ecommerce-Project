package com.example.ecommerce.project.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ecommerce.project.exceptions.NotFoundException;
import com.example.ecommerce.project.model.Product;
import com.example.ecommerce.project.services.ProductService;

@RestController
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	Logger logger = Logger.getLogger(getClass().getName());

	/**
	 * This function help us to retrieve products
	 * 
	 * @return {@link List} of Product {@link Product}
	 */
	@GetMapping("/public/products")
	public ResponseEntity<List<Product>> getAllProductDetails(
			@RequestParam(required = false,value="limit",defaultValue = "0")Integer limit,
			@RequestParam (required = false,value="offset",defaultValue = "0")Integer offset,
			@RequestParam (required = false, value="sortBy",defaultValue = "productId")String sortBy,
			@RequestParam (required = false, value="sortDir",defaultValue = "DESC")String sortDir) {
		List<Product> products = productService.getAllProducts(limit, offset, sortBy, sortDir);
		if (products == null || products.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to retrieve the products");
	    }
	    return new ResponseEntity<>(products, HttpStatus.OK);
		
	}
	
	/**
	 * This function help us to retrieve product
	 * 
	 * @return Product {@link Product}
	 */
	@GetMapping("/public/products/{productId}")
	public ResponseEntity<Product> getSingleProductDetail(@PathVariable Integer productId) {
		Product products = productService.getSingleProduct(productId);
		    return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	/**
	 * This function help us to create products
	 * 
	 * @param Product {@link Product}
	 * @return String (confirmation message)
	 */
	@PostMapping("/public/products")
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		 boolean isCreated = productService.createProduct(product);
		    if (isCreated) {
		        return new ResponseEntity<>("New Product added successfully", HttpStatus.CREATED);
		    } else {
		        return new ResponseEntity<>("Failed to add new product", HttpStatus.BAD_REQUEST);
		    }
	}
	
	/**
	 * This function help us to delete product
	 * @param product Id
	 * @return String (confirmation message)
	 */
	@DeleteMapping("/public/products/{productId}")
	public ResponseEntity<String> deleteProductDetail(@PathVariable Integer productId) {
	    try {
	        // Attempt to delete the product
	        String productStatus = productService.deleteProduct(productId); 
	        // Log the successful deletion
	        logger.info(String.format("Deleted Product ID: %d", productId));
	        return new ResponseEntity<>(productStatus, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        // Log the failure if the product was not found
	        logger.info(String.format("Failed to delete Product ID: %d - %s", productId, e.getMessage()));
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        // Log unexpected exceptions
	        logger.severe(String.format("Unexpected error occurred while deleting Product ID: %d - %s", productId, e.getMessage()));
	        return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	/**
	 * This function help us to update products
	 * @param product Id
	 * @return String (confirmation message)
	 */
	@PutMapping("/public/products/{productId}")
	public ResponseEntity<String> updateProductDetail(@RequestBody Product product, @PathVariable Integer productId) {
		try {
			productService.updateProduct(product, productId);
			return new ResponseEntity<>("Product Detail with: " + productId + " updated successfully",HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.info("Failed to update " + productId + " - " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}

}
