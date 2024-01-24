package com.ecommerce.ui.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
	
@Entity
@Table(name = "products")
public class Products {
	
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "discount_per")
	private double discountPercentage;
	
	@Column(name = "rating")
	private double rating;
	
	@Column(name = "stock")
	private int stock;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cat_id", referencedColumnName = "id")
	private Category category;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_cat_id", referencedColumnName = "id")
	private SubCategory subCategory;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_sub_cat_id", referencedColumnName = "id")
	private SubSubCategory subSubCategory;
	
	@Column(name = "brand")
	private String brand;
	
	
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "prod_id")
	private List<ProductImages> images = new ArrayList<>();


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<ProductImages> getImages() {
		return images;
	}

	public void setImages(List<ProductImages> images) {
		this.images = images;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public SubSubCategory getSubSubCategory() {
		return subSubCategory;
	}

	public void setSubSubCategory(SubSubCategory subSubCategory) {
		this.subSubCategory = subSubCategory;
	}

	
	
	
	

}
