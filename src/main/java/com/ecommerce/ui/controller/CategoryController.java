package com.ecommerce.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ui.entity.Category;
import com.ecommerce.ui.repository.CategoriesRepository;
import com.ecommerce.ui.service.CategoryService;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping("/{id}")
    public String getCategoryById(@PathVariable int id ){
		
		System.out.println("categoryID  "+id);
        return categoryService.getCategoryById(id);
    }
	@GetMapping("")
	public String getAllCategories(){
		return categoryService.getAllCategorytList();
	}
	
	
	

}
