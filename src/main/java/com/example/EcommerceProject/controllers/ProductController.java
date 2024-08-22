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

import com.example.EcommerceProject.exceptions.NotFoundException;
import com.example.EcommerceProject.model.Product;
import com.example.EcommerceProject.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/public/products")
	public ResponseEntity<List<Product>> getAllProductDetails() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@GetMapping("/public/products/{product_Id}")
	public ResponseEntity<Product> getSingleProductDetail(@PathVariable Integer product_Id) {
		Product products = productService.getSingleProduct(product_Id);
		    return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	/**
	 * This function help us to create products
	 * @param product
	 * @return
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
	
	@DeleteMapping("/public/products/{product_Id}")
	public ResponseEntity<String> deleteProductDetail(@PathVariable Integer product_Id) {
		try {
			String productStatus = productService.deleteProduct(product_Id);
			System.out.println("Deleted Product ID: " + product_Id);
			return new ResponseEntity<>(productStatus, HttpStatus.OK);
		} catch (NotFoundException e) {
			 System.err.println("Failed to delete Product ID: " + product_Id + " - " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/public/products/{product_Id}")
	public ResponseEntity<String> updateProductDetail(@RequestBody Product product, @PathVariable Integer product_Id) {
		try {
			Product savedProduct = productService.updateProduct(product, product_Id);
			return new ResponseEntity<>("Product Detail with: " + product_Id + " updated successfully",HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
		
	}

}
