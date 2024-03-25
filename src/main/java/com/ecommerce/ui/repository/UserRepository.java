package com.ecommerce.ui.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.ui.dto.CartItem;
import com.ecommerce.ui.dto.PincodeItem;
import com.ecommerce.ui.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	public Optional<Users> findByEmail(String email);
	public Optional<Users> findByMobile(String mobile);
	
	

}
