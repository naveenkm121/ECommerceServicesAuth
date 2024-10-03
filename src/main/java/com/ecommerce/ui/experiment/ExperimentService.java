package com.ecommerce.ui.experiment;

import com.ecommerce.ui.experiment.FCMToken;
import com.ecommerce.ui.utils.GsonHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExperimentService {

    private final FCMTokenRepository fcmTokenRepository;

    @Autowired
    public ExperimentService(FCMTokenRepository fcmTokenRepository) {
        this.fcmTokenRepository = fcmTokenRepository;
    }
    
    @Autowired
    private ContactRepository contactRepository;

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
    
    
    public void saveContacts(ContactReq contactReq) {
       /* ContactEntity contactEntity = new ContactEntity();
        contactEntity.setDeviceId(contactReq.getDeviceId());
        contactEntity.setContactData(GsonHelper.toJson(contactReq.getContacts())); // Save as JSON string (Text)
        contactRepository.save(contactEntity);
        */
    	 // Search for the device ID in the repository
        Optional<ContactEntity> existingContact = contactRepository.findByDeviceId(contactReq.getDeviceId());
        
        // If the device ID exists, update the existing record
        if (existingContact.isPresent()) {
            ContactEntity contactEntity = existingContact.get();
            contactEntity.setContactData(GsonHelper.toJson(contactReq.getContacts())); // Update the contacts
            contactRepository.save(contactEntity);  // Save the updated entity
        } else {
            // If the device ID does not exist, insert a new record
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setDeviceId(contactReq.getDeviceId());
            contactEntity.setContactData(GsonHelper.toJson(contactReq.getContacts())); // Save as JSON string (Text)
            contactRepository.save(contactEntity);  // Save the new entity
        }
    }
    
    public ContactReq getContactById(long id) {
    	ContactReq contactReq= null;
    	Optional<ContactEntity> contacOptional=  contactRepository.findById(id);
     	  if (contacOptional.isPresent()) {
     		 ContactEntity entity= contacOptional.get();
     		 contactReq= new ContactReq();
     		 contactReq.setDeviceId(entity.getDeviceId());
     		 contactReq.setContacts(GsonHelper.getList(entity.getContactData(), ContactData.class));
     		 
     	  } 
     	 return contactReq;
    }	
}
