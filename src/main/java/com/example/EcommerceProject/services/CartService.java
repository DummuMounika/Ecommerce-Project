package com.example.EcommerceProject.services;

import java.util.List;

import com.example.EcommerceProject.model.AddToCart;
import com.example.EcommerceProject.model.CartResponse;


public interface CartService {
	
	public List<AddToCart> getAllCartDetails();
	public boolean addCart(AddToCart addCart);
	public String deleteCartDetails(int cartId);
	public CartResponse getCartDetails(int customerId);
}
