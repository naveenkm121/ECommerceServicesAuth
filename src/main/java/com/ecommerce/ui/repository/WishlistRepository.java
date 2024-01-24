package com.ecommerce.ui.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.ui.dto.WishlistDTO;
import com.ecommerce.ui.entity.Banners;
import com.ecommerce.ui.entity.Products;
import com.ecommerce.ui.entity.Users;
import com.ecommerce.ui.entity.Wishlist;


@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
	
	
    @Query(value = "SELECT w.id,w.prod_id as prodId,p.title,p.brand,p.price,p.discount_per,p.thumbnail  FROM ecommerce.products p INNER JOIN ecommerce.wishlist w ON p.id = w.prod_id WHERE w.user_id = :userId", nativeQuery = true)
    List<WishlistDTO> getWishlistByUserId(@Param("userId") Long userId);
    
    public Optional<Wishlist> findByProdIdAndUserId(Long prodId,Long userId);

}