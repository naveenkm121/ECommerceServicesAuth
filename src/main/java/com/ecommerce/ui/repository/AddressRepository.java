package com.ecommerce.ui.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.ui.dto.PincodeItem;
import com.ecommerce.ui.dto.WishlistDTO;
import com.ecommerce.ui.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByUserId(long userId);
	
	@Query(value = "SELECT p.pincode,p.locality,d.district,s.state FROM ecommerce.pincode p,ecommerce.district d,ecommerce.state s WHERE p.district_id= d.id and d.state_id= s.id and p.pincode=:pincode", nativeQuery = true)
	List<PincodeItem> getPincodeDetails(@Param("pincode") String pincode);
	
	 @Modifying
	 @Transactional
	 @Query(value="UPDATE ecommerce.address a SET a.is_default = 1 WHERE a.user_id = :userId AND a.id = :id",nativeQuery = true)
	 void setDefaultAddress(@Param("userId") Long userId,@Param("id") int id);

	 @Modifying
	 @Transactional
	 @Query(value = "UPDATE ecommerce.address a SET a.is_default = 0 WHERE a.user_id = :userId AND a.id != :id",nativeQuery = true)
	 void unsetOtherDefaultAddresses(@Param("userId") Long userId,@Param("id") int id);

}
