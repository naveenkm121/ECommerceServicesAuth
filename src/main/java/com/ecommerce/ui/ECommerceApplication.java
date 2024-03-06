package com.ecommerce.ui;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@RestController
@SpringBootApplication
/*@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })*/
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
		System.out.println("E Commerce Project started git");
		try {
			initializeFirebase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value={"", "/","/home", "/welcome"})
	public String welcome() {
		return "Welcome to the ECommerce Rest API's";
	}
	
	
    private static void initializeFirebase() throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("serviceAccountKey.json").getInputStream()))
                .build();
        FirebaseApp.initializeApp(options);
    }
}
