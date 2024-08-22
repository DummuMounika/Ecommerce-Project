package com.example.EcommerceProject.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.EcommerceProject.exceptions.NotFoundException;
import com.example.EcommerceProject.model.AddToCart;
import com.example.EcommerceProject.model.CartResponse;
import com.example.EcommerceProject.model.CartProduct;
import com.example.EcommerceProject.model.Customer;
import com.example.EcommerceProject.model.Product;
import com.example.EcommerceProject.modelEntity.AddToCartEntity;
import com.example.EcommerceProject.modelEntity.ProductEntity;
import com.example.EcommerceProject.repository.CartRepository;
import com.example.EcommerceProject.repository.ProductRepository;

@Service
public class CartServiceImplement  implements CartService{

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;


	//convertMethods
	private AddToCart convertCartEntityToCart(AddToCartEntity addToCartEntity) {
		return new AddToCart(addToCartEntity.getCart_id(),addToCartEntity.getCustomer_id(),
				addToCartEntity.getProduct_id(),addToCartEntity.getQuantity(),addToCartEntity.getUnit_price(),
				addToCartEntity.getCart_created_time(),addToCartEntity.getCart_updated_time());
	}

	private List<AddToCart> convertCartEntityListToCartList(List<AddToCartEntity> addToCartEntityList){
		List<AddToCart> addToCartList = new ArrayList<AddToCart>();
		for(AddToCartEntity addToCartEntity: addToCartEntityList) {
			AddToCart addToCart = convertCartEntityToCart(addToCartEntity);
			addToCartList.add(addToCart);
		}
		return addToCartList;
	}

	private AddToCartEntity convertAddToCartToAddToCartEntity(AddToCart addToCart) {
		return new AddToCartEntity(addToCart.getCustomer_id(),addToCart.getProduct_id(),
				addToCart.getQuantity(),addToCart.getUnit_price());
	}

	private void validateCartDetails(AddToCart addToCart, Product productEntity) {

		// Logic to check customer Id existence
		Customer customerEntity = customerService.getSingleCustomerInfo(addToCart.getCustomer_id());
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
		int presentProductStock = productEntity.getProduct_stock_quantity();
		double productPrice = productEntity.getProduct_price();

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
		List<AddToCart> addToCartList = convertCartEntityListToCartList(addToCartEntityList);
		return addToCartList;	
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
		// Validate and calculate cart details
		Product productEntity = productService.getSingleProduct(addToCart.getProduct_id());
		validateCartDetails(addToCart, productEntity);
		double unitPrice  = calculateCartDetails(addToCart, productEntity);
		addToCart.setUnit_price(unitPrice);

		// Fetch current timestamp
		Timestamp currentTimestamp = cartRepository.findCurrentTimeStamp();

		// Check if the product is already in the cart
		Optional<AddToCartEntity> productInCartOpt = cartRepository.findByCustomerIdAndProductId(
				addToCart.getCustomer_id(), addToCart.getProduct_id());

		AddToCartEntity entity = null;
		if (productInCartOpt.isPresent()) {
			// If the product is already in the cart, update quantity and price
			AddToCartEntity existingCart = productInCartOpt.get();
			existingCart.setQuantity(addToCart.getQuantity());
			existingCart.setUnit_price(addToCart.getUnit_price());
			existingCart.setCart_updated_time(currentTimestamp);
			entity = cartRepository.save(existingCart);
		} else {
			// If the product is new, add it with the same cart_id
			AddToCartEntity newCartEntity = convertAddToCartToAddToCartEntity(addToCart);
			newCartEntity.setCart_created_time(currentTimestamp);
			newCartEntity.setCart_updated_time(currentTimestamp);
			entity = cartRepository.save(newCartEntity);
		}
		if (entity == null) {
			return false;
		}else {
			return true;
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

		List<Integer> productIds =  new ArrayList<Integer>();
		for (AddToCartEntity cartEntity: cartEntities) {
			productIds.add(cartEntity.getProduct_id());
		}
		

		HashMap<Integer, Product> productMap = productService.getMultipleProducts(productIds);

		for (AddToCartEntity cartEntity : cartEntities) {
			AddToCart cart = convertCartEntityToCart(cartEntity);

			Product product = productMap.get(cartEntity.getProduct_id());

			CartProduct cartProduct = new CartProduct(
					product.getProduct_id(),
					product.getProduct_name(),
					product.getProduct_price(),
					cartEntity.getQuantity());

			checkOutStatus(cart, cartProduct, product);
			cartProducts.add(cartProduct);
			totalPrice += cartProduct.getProductUnitPrice();
		}
		if(cartProducts.size()==0)
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




