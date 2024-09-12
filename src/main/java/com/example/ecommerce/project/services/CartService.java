package com.example.ecommerce.project.services;

import java.util.List;

import com.example.ecommerce.project.model.AddToCart;
import com.example.ecommerce.project.model.CartResponse;

public interface CartService {

	public List<AddToCart> getAllCartDetails();

	public boolean addCart(AddToCart addCart);

	public String deleteCartDetails(int cartId);

	public CartResponse getCartDetails(int customerId);
}
