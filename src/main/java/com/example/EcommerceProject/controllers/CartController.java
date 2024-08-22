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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.EcommerceProject.model.AddToCart;
import com.example.EcommerceProject.model.CartResponse;
import com.example.EcommerceProject.services.CartService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/public/carts")
	public ResponseEntity<List<AddToCart>> getAllCarttDetails() {
		List<AddToCart> carts = cartService.getAllCartDetails();
		return new ResponseEntity<>(carts, HttpStatus.OK);
	}
	

	@PostMapping("/public/carts")
	public ResponseEntity<String> createCart(@RequestBody AddToCart cart) {
		boolean isCreated = cartService.addCart(cart);
		if (isCreated) {
			return new ResponseEntity<>("New Cart added successfully",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Cart failed to save",HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/public/carts/{cart_Id}")
	public ResponseEntity<String> deleteCartDetail(@PathVariable Integer cart_Id) {
		try {
			String cartStatus = cartService.deleteCartDetails(cart_Id);
		    return new ResponseEntity<>(cartStatus, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}	
	}
	
	@GetMapping("/public/cartDetails")
	public ResponseEntity<CartResponse> cartDetails(@RequestParam(value="customerId") Integer customer_id) {
		System.out.println("Hi");
		CartResponse cart = cartService.getCartDetails(customer_id);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	

}
