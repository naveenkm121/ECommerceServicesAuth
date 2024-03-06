package com.ecommerce.ui.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FcmNotificationService {

    public String  sendNotification(PushNotificationRequest pushNotificationRequest) {
        Message message = Message.builder()
                .setToken(pushNotificationRequest.getToken())
                .putData("title", pushNotificationRequest.getTitle())
                .putData("body", pushNotificationRequest.getMessage())
                .build();
        try {
            FirebaseMessaging.getInstance().send(message);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Notification send successfully";
    }
}
