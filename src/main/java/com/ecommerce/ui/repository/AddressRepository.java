package com.ecommerce.ui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ui.dto.WishlistDTO;
import com.ecommerce.ui.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByUserId(long userId);


}
