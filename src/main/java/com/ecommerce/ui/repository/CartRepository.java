package com.ecommerce.ui.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.ui.dto.CartItem;
import com.ecommerce.ui.dto.WishlistDTO;
import com.ecommerce.ui.entity.Cart;
import com.ecommerce.ui.entity.Wishlist;

public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(value = "SELECT cart.id,cart.quantity,cart.prod_id as prodId,p.title,p.brand,ROUND(p.price,2) as price,p.discount_per,p.thumbnail,ROUND(p.price - (p.price * (p.discount_per / 100)),2) AS discountPrice,ROUND((cart.quantity*p.price),2) as totalPrice,ROUND(cart.quantity*(p.price - (p.price * (p.discount_per / 100))),2) as totalDiscountPrice  FROM ecommerce.products p INNER JOIN ecommerce.cart cart ON p.id = cart.prod_id WHERE cart.user_id = :userId", nativeQuery = true)
	List<CartItem> getCartItemByUserId(@Param("userId") Long userId);
	
    public Optional<Cart> findByProdIdAndUserId(Long prodId,Long userId);

}
