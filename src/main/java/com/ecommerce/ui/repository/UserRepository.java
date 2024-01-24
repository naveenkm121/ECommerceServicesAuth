package com.ecommerce.ui.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ui.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	public Optional<Users> findByEmail(String email);
	public Optional<Users> findByMobile(String mobile);


}
