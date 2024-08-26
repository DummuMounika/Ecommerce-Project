package com.example.EcommerceProject.controllers;

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

import com.example.EcommerceProject.exceptions.NotFoundException;
import com.example.EcommerceProject.model.Product;
import com.example.EcommerceProject.services.ProductService;

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
			@RequestParam (required = false, value="sortBy",defaultValue = "product_id")String sortBy,
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
			String productStatus = productService.deleteProduct(productId);
			logger.info("Deleted Product ID: " + productId);
			return new ResponseEntity<>(productStatus, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.info("Failed to delete Product ID: " + productId + " - " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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
