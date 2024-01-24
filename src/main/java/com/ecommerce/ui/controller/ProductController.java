package com.ecommerce.ui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ui.constant.AppConstants;
import com.ecommerce.ui.entity.Category;
import com.ecommerce.ui.entity.ProductImages;
import com.ecommerce.ui.entity.Products;
import com.ecommerce.ui.repository.CategoriesRepository;
import com.ecommerce.ui.repository.ProductRepository;
import com.ecommerce.ui.service.ProductService;
import com.ecommerce.ui.utils.ProductFilterEnum;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	
	///*************** Create  Product    ***********  *******************
	
	
	 @PostMapping("")
	 public String createProduct(@RequestBody Products product) {
	    return productService.createProduct(product);
	 }
	  

	///***************  Product Details by productId  ********************
	@GetMapping("/{id}")
    public String getProductById(@PathVariable int id ){
		
		System.out.println("productID  "+id);
        return productService.getProductById(id);
    }
	
	///***************  Product List  **********************************
	@GetMapping("")
    public String getAllproducts(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
		
       // return productService.getProductsByFilters(pageNo, pageSize, sortBy, sortDir, ProductFilterEnum.NO_FILTER_WISE, null);
        return productService.getProductsByFilters(pageNo, pageSize, sortBy, sortDir, ProductFilterEnum.NO_FILTER_WISE, 0);
    }
	
	
	///***************  Product Details by productId  ********************
	@DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable int id ){
		
		System.out.println("productID  "+id);
        return productService.deleteProductById(id);
    }
	
	
	 @PatchMapping("")
	 public String updateProduct(@RequestBody Products product) {
	    return productService.updateProduct(product);
	 }

	
	@GetMapping("/categories")
	public List<Category> getAllCategories(){
		return categoriesRepository.findAll();
	}
	
/*	@GetMapping("category/{catId}")
    public List<Products> getProductsByCategoryId(@PathVariable int id ){
		
		System.out.println("productID  "+id);
        return productRepository.findByCategoryId(id);
    }*/
	
	@GetMapping("category/{catId}")
    public String getProductsByCategoryId(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @PathVariable int catId
    ){
		
        return productService.getProductsByFilters(pageNo, pageSize, sortBy, sortDir, ProductFilterEnum.CATEGORY_WISE, catId);
    }
	
	@GetMapping("subcategory/{subCatId}")
    public String getProductsBySubCategoryId(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @PathVariable int subCatId
    ){
		
		 return productService.getProductsByFilters(pageNo, pageSize, sortBy, sortDir, ProductFilterEnum.SUB_CATEGORY_WISE, subCatId);
    }

	@GetMapping("subsubcategory/{subSubCatId}")
    public String getProductsBySubSubCategoryId(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @PathVariable int subSubCatId
    ){
		
		return productService.getProductsByFilters(pageNo, pageSize, sortBy, sortDir, ProductFilterEnum.SUB_SUB_CATEGORY_WISE, subSubCatId);
    }
}
