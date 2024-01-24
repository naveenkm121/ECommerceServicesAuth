package com.ecommerce.ui.dto;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.ui.entity.ProductImages;

public class CartDTO {

	private long userId;
	private double totalPrice;
	private double totalDiscountPrice;
	private int totalProducts;
	private int totalQuantity;
	private List<CartItem> cartItems = new ArrayList<>();
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalDiscountPrice() {
		return totalDiscountPrice;
	}
	public void setTotalDiscountPrice(double totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}
	public int getTotalProducts() {
		return totalProducts;
	}
	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	
	
	
	
 

}
