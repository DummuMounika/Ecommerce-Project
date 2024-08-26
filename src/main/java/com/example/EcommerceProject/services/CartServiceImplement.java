package com.example.EcommerceProject.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.EcommerceProject.exceptions.NotFoundException;
import com.example.EcommerceProject.model.AddToCart;
import com.example.EcommerceProject.model.CartProduct;
import com.example.EcommerceProject.model.CartResponse;
import com.example.EcommerceProject.model.Customer;
import com.example.EcommerceProject.model.Product;
import com.example.EcommerceProject.modelEntity.AddToCartEntity;
import com.example.EcommerceProject.repository.CartRepository;

@Service
public class CartServiceImplement  implements CartService{

	private CartRepository cartRepository;
	private ProductService productService;
	private CustomerService customerService;
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public CartServiceImplement(CartRepository cartRepository, ProductService productService,
			CustomerService customerService) {
		super();
		this.cartRepository = cartRepository;
		this.productService = productService;
		this.customerService = customerService;
	}

	//convertMethods
	private AddToCart convertCartEntityToCart(AddToCartEntity addToCartEntity) {
		return new AddToCart(addToCartEntity.getCartId(),addToCartEntity.getCustomerId(),
				addToCartEntity.getProductId(),addToCartEntity.getQuantity(),addToCartEntity.getUnitPrice(),
				addToCartEntity.getCartCreatedTime(),addToCartEntity.getCartUpdatedTime());
	}

	private List<AddToCart> convertCartEntityListToCartList(List<AddToCartEntity> addToCartEntityList){
		List<AddToCart> addToCartList = new ArrayList<>();
		for(AddToCartEntity addToCartEntity: addToCartEntityList) {
			AddToCart addToCart = convertCartEntityToCart(addToCartEntity);
			addToCartList.add(addToCart);
		}
		return addToCartList;
	}

	private AddToCartEntity convertAddToCartToAddToCartEntity(AddToCart addToCart) {
		return new AddToCartEntity(addToCart.getCustomerId(),addToCart.getProductId(),
				addToCart.getQuantity(),addToCart.getUnitPrice());
	}

	private void validateCartDetails(AddToCart addToCart, Product productEntity) {

		// Logic to check customer Id existence
		Customer customerEntity = customerService.getSingleCustomerInfo(addToCart.getCustomerId());
		if (customerEntity == null) {
			throw new NotFoundException("Customer Id not Found");
		}

		if (productEntity == null) {
			throw new NotFoundException("Product Id not Found");
		}
	}

	private double calculateCartDetails(AddToCart addToCart, Product productEntity) {


		// Logic to check product quantity presence
		int enteredQuantity = addToCart.getQuantity();
		int presentProductStock = productEntity.getProductStockQuantity();
		double productPrice = productEntity.getProductPrice();

		if (enteredQuantity > presentProductStock) {
			throw new NotFoundException("Unable to add the requested quantity to the cart. Only " + presentProductStock + " items are currently in stock.");
		} else {
			return enteredQuantity * productPrice;
		}
	}

	//CRUD Methods
	@Override
	public List<AddToCart> getAllCartDetails() {
		List<AddToCartEntity> addToCartEntityList = cartRepository.findAll();
		return convertCartEntityListToCartList(addToCartEntityList);
	}


	@Override
	public String deleteCartDetails(int cartId) {
		AddToCartEntity removeCartEntity = cartRepository.findById(cartId)
				.orElseThrow(() -> new NotFoundException("cart Id not found"));
		cartRepository.delete(removeCartEntity);
		return "The cart " + cartId + " deleted successfully";
	}

	@Override
	public boolean addCart(AddToCart addToCart) {
	    try {
	        // Validate and calculate cart details
	        Product productEntity = productService.getSingleProduct(addToCart.getProductId());
	        validateCartDetails(addToCart, productEntity);
	        double unitPrice = calculateCartDetails(addToCart, productEntity);
	        addToCart.setUnitPrice(unitPrice);

	        // Fetch current timestamp
	        Timestamp currentTimestamp = cartRepository.findCurrentTimeStamp();

	        // Check if the product is already in the cart
	        Optional<AddToCartEntity> productInCartOpt = cartRepository.findByCustomerIdAndProductId(
	                addToCart.getCustomerId(), addToCart.getProductId());

	        if (productInCartOpt.isPresent()) {
	            // Update quantity, price, and timestamp for the existing product
	            AddToCartEntity existingCart = productInCartOpt.get();
	            existingCart.setQuantity(addToCart.getQuantity());
	            existingCart.setUnitPrice(addToCart.getUnitPrice());
	            existingCart.setCartUpdatedTime(currentTimestamp);
	            cartRepository.save(existingCart);
	        } else {
	            // Add new product to the cart with timestamps
	            AddToCartEntity newCartEntity = convertAddToCartToAddToCartEntity(addToCart);
	            newCartEntity.setCartCreatedTime(currentTimestamp);
	            newCartEntity.setCartUpdatedTime(currentTimestamp);
	            cartRepository.save(newCartEntity);
	        }

	        return true;
	    } catch (Exception e) {
	        // Log the exception
	        logger.severe("Failed to add/update cart: " + e.getMessage());
	        return false;
	    }
	}


	@Override
	public CartResponse getCartDetails(int customerId) {

		List<AddToCartEntity> cartEntities = cartRepository.findAllByCustomerId(customerId);

		if (cartEntities==null) {
			throw new NotFoundException("Cart not found for customer Id");
		} 

		List<CartProduct> cartProducts = new ArrayList<>();
		double totalPrice = 0;

		List<Integer> productIds =  new ArrayList<>();
		for (AddToCartEntity cartEntity: cartEntities) {
			productIds.add(cartEntity.getProductId());
		}
		

		Map<Integer, Product> productMap = productService.getMultipleProducts(productIds);

		for (AddToCartEntity cartEntity : cartEntities) {
			AddToCart cart = convertCartEntityToCart(cartEntity);

			Product product = productMap.get(cartEntity.getProductId());

			CartProduct cartProduct = new CartProduct(
					product.getProductId(),
					product.getProductName(),
					product.getProductPrice(),
					cartEntity.getQuantity());

			checkOutStatus(cart, cartProduct, product);
			cartProducts.add(cartProduct);
			totalPrice += cartProduct.getProductUnitPrice();
		}
		if(cartProducts.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, add products in the cart");
		else
			return new CartResponse(cartProducts, totalPrice);
	}

	public void checkOutStatus(AddToCart addcart, CartProduct cartProduct, Product product) {
		try {
			double unitPrice  = calculateCartDetails(addcart, product);
			cartProduct.setProductUnitPrice(unitPrice);
			cartProduct.setProductReadyToCheckOut(true);
		}catch (NotFoundException e) {
			cartProduct.setProductReadyToCheckOut(false);
			cartProduct.setErrorMessage(e.getMessage());
		}
	}
}




