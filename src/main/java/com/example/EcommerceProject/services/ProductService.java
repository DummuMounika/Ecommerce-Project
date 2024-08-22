package com.example.EcommerceProject.services;

import java.util.HashMap;
import java.util.List;

import com.example.EcommerceProject.model.Product;
import com.example.EcommerceProject.modelEntity.ProductEntity;


public interface ProductService {
	
	public List<Product> getAllProducts();
	public boolean createProduct(Product product);
	public String deleteProduct(int product_Id);
	public Product updateProduct(Product product, int product_Id);
	public Product getSingleProduct(int product_Id);
	public List<Product> convertProductEntityListToProductList(List<ProductEntity> productEntityList);
    public HashMap<Integer, Product> getMultipleProducts(List<Integer> productIds);
}
