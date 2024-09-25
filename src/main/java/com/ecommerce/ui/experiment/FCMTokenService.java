package com.ecommerce.ui.experiment;

import com.ecommerce.ui.experiment.FCMToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FCMTokenService {

    private final FCMTokenRepository fcmTokenRepository;

    @Autowired
    public FCMTokenService(FCMTokenRepository fcmTokenRepository) {
        this.fcmTokenRepository = fcmTokenRepository;
    }

    /**
     * Saves a new FCM token or updates an existing one.
     *
     * @param fcmToken the FCMToken object to be saved or updated
     * @return the saved or updated FCMToken
     */
    public FCMToken saveOrUpdateToken(FCMToken fcmToken) {
        Optional<FCMToken> existingToken = fcmTokenRepository.findByUserIdAndDeviceId(fcmToken.getUserId(), fcmToken.getDeviceId());
        
        if (existingToken.isPresent()) {
            FCMToken tokenToUpdate = existingToken.get();
            tokenToUpdate.setToken(fcmToken.getToken());
            tokenToUpdate.setDeviceModel(fcmToken.getDeviceModel());
            tokenToUpdate.setOsVersion(fcmToken.getOsVersion());
            return fcmTokenRepository.save(tokenToUpdate);
        } else {
            return fcmTokenRepository.save(fcmToken);
        }
    }

    /**
     * Finds an FCMToken by userId and deviceId.
     *
     * @param userId the user ID
     * @param deviceId the device ID
     * @return an Optional containing the found FCMToken, or empty if not found
     */
    public Optional<FCMToken> findByUserIdAndDeviceId(Long userId, String deviceId) {
        return fcmTokenRepository.findByUserIdAndDeviceId(userId, deviceId);
    }

    /**
     * Deletes an FCMToken by its ID.
     *
     * @param id the ID of the FCMToken to be deleted
     */
    public void deleteToken(Long id) {
        fcmTokenRepository.deleteById(id);
    }
}
