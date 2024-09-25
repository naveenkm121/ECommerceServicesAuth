package com.ecommerce.ui.experiment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FCMTokenRepository extends JpaRepository<FCMToken, Long> {
    
    Optional<FCMToken> findByUserIdAndDeviceId(Long userId, String deviceId);
}