package com.ecommerce.ui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.ui.dto.PincodeItem;
import com.ecommerce.ui.dto.WishlistDTO;
import com.ecommerce.ui.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByUserId(long userId);
	
	@Query(value = "SELECT p.pincode,p.locality,d.district,s.state FROM ecommerce.pincode p,ecommerce.district d,ecommerce.state s WHERE p.district_id= d.id and d.state_id= s.id and p.pincode=:pincode", nativeQuery = true)
	List<PincodeItem> getPincodeDetails(@Param("pincode") String pincode);

}
