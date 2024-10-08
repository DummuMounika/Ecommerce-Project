package com.example.ecommerce.project.services;

import java.util.List;
import java.util.Map;

import com.example.ecommerce.project.model.Product;
import com.example.ecommerce.project.model.entity.ProductEntity;


public interface ProductService {
	
	public List<Product> getAllProducts(int limit, int offset, String sortBy, String sortDirection);
	public boolean createProduct(Product product);
	public String deleteProduct(int productId);
	public Product updateProduct(Product product, int productId);
	public Product getSingleProduct(int productId);
	public List<Product> convertProductEntityListToProductList(List<ProductEntity> productEntityList);
    public Map<Integer, Product> getMultipleProducts(List<Integer> productIds);
    
    
    
}
