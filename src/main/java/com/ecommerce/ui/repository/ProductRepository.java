package com.ecommerce.ui.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.ui.entity.Products;
import com.ecommerce.ui.entity.Users;


@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
	
	
	 public List<Products> findByCategoryId(int id);
	 public Page<Products> findByCategoryId(int catId, Pageable pageable);
	 public Page<Products> findBySubCategoryId(int subCatId, Pageable pageable);
	 public Page<Products> findBySubSubCategoryId(int subSubCatId, Pageable pageable);
	 public Page<Products> findByBrand(String brand , Pageable pageable);
	

}
