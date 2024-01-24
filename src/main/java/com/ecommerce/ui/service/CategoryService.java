package com.ecommerce.ui.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.ui.constant.AppConstants;
import com.ecommerce.ui.dto.BaseApiResModel;
import com.ecommerce.ui.dto.PageableApiResModel;
import com.ecommerce.ui.entity.Category;
import com.ecommerce.ui.entity.Products;
import com.ecommerce.ui.repository.CategoriesRepository;
import com.ecommerce.ui.repository.ProductRepository;
import com.ecommerce.ui.utils.GsonHelper;


@Service
public class CategoryService {

	
	@Autowired CategoriesRepository categoriesRepository;
	@Autowired GsonHelper gsonHelper;
	
	
	
	public String  getCategoryById(int id){
		
		BaseApiResModel apiResponse =  new BaseApiResModel();
		Optional<Category> categoryDetail= categoriesRepository.findById(id);
		
		if(categoryDetail.isPresent())
		{
			apiResponse.setData(categoryDetail.get());
			apiResponse.setStatus(1);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
			
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
		}
		
		return  gsonHelper.toJson(apiResponse);
	}



	public String getAllCategorytList() {
		
		BaseApiResModel apiResponse =  new BaseApiResModel();
        List<com.ecommerce.ui.entity.Category> categories = categoriesRepository.findAll();
        if(categories!=null){
        	apiResponse.setStatus(1);
			if(categories.size()>0){
				apiResponse.setMessage(AppConstants.SUCCESS_MSG);
				apiResponse.setData(categories);
			}else{
				apiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
			}
			
        }else{
        	apiResponse.setStatus(0);
        	apiResponse.setMessage(AppConstants.FAILURE_MSG);
        }
       // System.out.println("categories =="+categories.get(0).getSubCategories().get(0).getSubSubCategories().get(0).getSubCategory().getName());
        
		return gsonHelper.toJson(apiResponse);
	}
}
