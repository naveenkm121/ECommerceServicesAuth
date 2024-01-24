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

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "sub_categories")
public class SubCategory {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "cat_id")
	private int catId;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "sub_cat_id")
	private List<SubSubCategory> subSubCategories = new ArrayList<>();
	
/*	@OneToMany(mappedBy = "subCategory",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<SubSubCategory> subSubCategories = new ArrayList<>();
	*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public List<SubSubCategory> getSubSubCategories() {
		return subSubCategories;
	}

	public void setSubSubCategories(List<SubSubCategory> subSubCategories) {
		this.subSubCategories = subSubCategories;
	}

	@Override
	public String toString() {
		return "SubCategory [id=" + id + ", name=" + name + ", subSubCategories=" + subSubCategories + "]";
	}
	
	
	
	
	
	

}