package com.ecommerce.ui.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ecommerce.ui.entity.Category;
import com.ecommerce.ui.entity.SubCategory;
import com.ecommerce.ui.entity.SubSubCategory;

public interface WishlistDTO {
	
	Long  getId();
	Long  getProdId();
	String getTitle();
	Integer getPrice();
	Double getDiscount_per();
	String getBrand();
	String getThumbnail();

}
