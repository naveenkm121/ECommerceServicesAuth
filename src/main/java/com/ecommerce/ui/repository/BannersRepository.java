package com.ecommerce.ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ui.entity.Banners;

@Repository
public interface BannersRepository extends JpaRepository<Banners, Long> {

}
