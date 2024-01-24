package com.ecommerce.ui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.ui.constant.AppConstants;
import com.ecommerce.ui.dto.BaseApiResModel;
import com.ecommerce.ui.dto.PageableApiResModel;
import com.ecommerce.ui.entity.Products;
import com.ecommerce.ui.repository.ProductRepository;
import com.ecommerce.ui.utils.BaseApiResponse;
import com.ecommerce.ui.utils.GsonHelper;
import com.ecommerce.ui.utils.PageableApiResponse;
import com.ecommerce.ui.utils.ProductFilterEnum;
//import com.ecommerce.ui.constants.URLConstants;


@Service
public class ProductService {
	
	@Autowired ProductRepository productRepository;
	@Autowired GsonHelper gsonHelper;
	
	
	
	
	public String  createProduct(Products product){
		
		BaseApiResModel apiResponse =  new BaseApiResModel();
	
		if(product!=null & !product.getTitle().equals(""))
		{
			Products createdProduct = productRepository.save(product);
			apiResponse.setData(createdProduct);
			apiResponse.setStatus(1);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
			
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage(AppConstants.FAILURE_MSG);
		}
		
		return  gsonHelper.toJson(apiResponse);
	}
	
	
	
	public String  getProductById(int id){
		
		BaseApiResModel apiResponse =  new BaseApiResModel();
		Optional<Products> productDetails= productRepository.findById(id);
		
		if(productDetails.isPresent())
		{
			apiResponse.setData(productDetails.get());
			apiResponse.setStatus(1);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
			
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
		}
		
		return  gsonHelper.toJson(apiResponse);
	}


/*
	public String getAllProductList(int pageNo, int pageSize, String sortBy, String sortDir) {
		
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Products> products = productRepository.findAll(pageable);
        PageableApiResModel pageableApiResponse = new PageableApiResModel();
        pageableApiResponse.setData(products.getContent());
		pageableApiResponse.setPageSize(products.getSize());
		pageableApiResponse.setTotalElements(products.getTotalElements());
		pageableApiResponse.setPageNo(products.getNumber());
		pageableApiResponse.setTotalPages(products.getTotalPages());
		pageableApiResponse.setIsLast(products.isLast());
        if(products!=null){
			pageableApiResponse.setStatus(1);
			if(products.getContent().size()>0){
				pageableApiResponse.setMessage(AppConstants.SUCCESS_MSG);
			}else{
				pageableApiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
			}
			
        }else{
			pageableApiResponse.setStatus(0);
			pageableApiResponse.setMessage(AppConstants.FAILURE_MSG);
        }
        
		return gsonHelper.toJson(pageableApiResponse);
	}
	*/
	
	
	public String getProductsByFilters(int pageNo, int pageSize, String sortBy, String sortDir,ProductFilterEnum productFilterEnu,int filterId) {
		
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Products> products= null;
		
        switch (productFilterEnu) {	
		
				case CATEGORY_WISE:
					products = productRepository.findByCategoryId(filterId,pageable);
					break;
					
				case SUB_CATEGORY_WISE:
					products = productRepository.findBySubCategoryId(filterId, pageable);
					break;
					
				case SUB_SUB_CATEGORY_WISE:
					products = productRepository.findBySubSubCategoryId(filterId, pageable);
					break;	
				
				case NO_FILTER_WISE:
					products = productRepository.findAll(pageable);
					break;
					
				default:
					products = productRepository.findAll(pageable);
					break;
		}

        PageableApiResModel pageableApiResponse = new PageableApiResModel();
        pageableApiResponse.setData(products.getContent());
		pageableApiResponse.setPageSize(products.getSize());
		pageableApiResponse.setTotalElements(products.getTotalElements());
		pageableApiResponse.setPageNo(products.getNumber());
		pageableApiResponse.setTotalPages(products.getTotalPages());
		pageableApiResponse.setIsLast(products.isLast());
        if(products!=null){
			pageableApiResponse.setStatus(1);
			if(products.getContent().size()>0){
				pageableApiResponse.setMessage(AppConstants.SUCCESS_MSG);
			}else{
				pageableApiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
			}
			
        }else{
			pageableApiResponse.setStatus(0);
			pageableApiResponse.setMessage(AppConstants.FAILURE_MSG);
        }
        
		return gsonHelper.toJson(pageableApiResponse);
}
	

	public String  updateProduct(Products newProduct){
		
		BaseApiResModel apiResponse =  new BaseApiResModel();
	
		Optional<Products> product  = productRepository.findById(newProduct.getId());
		if(product.isPresent())
		{ 
			productRepository.save(newProduct);
			apiResponse.setData(newProduct);
			apiResponse.setStatus(1);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
		}
		
		return  gsonHelper.toJson(apiResponse);
	}

	public String deleteProductById(int id) {
		BaseApiResModel apiResponse =  new BaseApiResModel();
	
		Optional<Products> product  = productRepository.findById(id);
		if(product.isPresent())
		{ 
			productRepository.deleteById(id);
			apiResponse.setData(product.get());
			apiResponse.setStatus(1);
			apiResponse.setMessage("Product with id ="+id+ " delete successfully");
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage("Product with id ="+id+ " Not Found");
		}
			       
		
		return gsonHelper.toJson(apiResponse);
	} 
	
	

}
