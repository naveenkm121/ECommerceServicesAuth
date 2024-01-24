package com.ecommerce.ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ui.entity.Category;
import com.ecommerce.ui.entity.ProductImages;

@Repository
public interface CategoriesRepository  extends JpaRepository<Category, Integer>{

}
