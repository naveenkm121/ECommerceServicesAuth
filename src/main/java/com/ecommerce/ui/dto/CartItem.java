package com.ecommerce.ui.dto;

public interface CartItem {

	Long getId();

	Long getProdId();

	String getTitle();

	Double getPrice();

	Double getDiscount_per();

	String getBrand();

	String getThumbnail();

	Integer getQuantity();

	Double getDiscountPrice();

	Double getTotalDiscountPrice();

	Double getTotalPrice();


}
