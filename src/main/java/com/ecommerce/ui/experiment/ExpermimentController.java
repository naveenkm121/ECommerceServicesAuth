package com.ecommerce.ui.experiment;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ui.constant.AppConstants;
import com.ecommerce.ui.utils.ProductFilterEnum;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/experiment")
public class ExpermimentController {
	
	
		@Autowired
		private  ExperimentService experimentService;

	   //@Value("${file.upload-dir}")
	    private String uploadDirPath="F:/UploadPicture/";
	    //private String uploadDirPath="E:/UploadPicture/";
	    
	    
	    
	    @PostMapping("/fcmToken")
	    public ResponseEntity<FCMToken> saveOrUpdateToken(@RequestBody FCMToken fcmToken) {
	        FCMToken savedToken = experimentService.saveOrUpdateToken(fcmToken);
	        return new ResponseEntity<>(savedToken, HttpStatus.OK);
	    }

	    /**
	     * Get an FCM token by userId and deviceId.
	     *
	     * @param userId the user ID
	     * @param deviceId the device ID
	     * @return the found FCMToken, or a 404 Not Found response if not found
	     */
	    @GetMapping("/fcmToken")
	    public ResponseEntity<FCMToken> getToken(@RequestParam Long userId, @RequestParam String deviceId) {
	        Optional<FCMToken> token = experimentService.findByUserIdAndDeviceId(userId, deviceId);
	        return token.map(fcmToken -> new ResponseEntity<>(fcmToken, HttpStatus.OK))
	                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    /**
	     * Delete an FCM token by its ID.
	     *
	     * @param id the ID of the FCMToken to be deleted
	     * @return a 204 No Content response if the deletion was successful
	     */
	    @DeleteMapping("/fcmToken/{id}")
	    public ResponseEntity<Void> deleteToken(@PathVariable Long id) {
	        experimentService.deleteToken(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    
	    @PostMapping("/contacts")
	    public ResponseEntity<String> saveContacts (@RequestBody ContactReq contactReq) {
	    	experimentService.saveContacts(contactReq);
	        return ResponseEntity.ok("Contacts JSON saved successfully!");
	    }
	    
	    @GetMapping("/contacts/{id}")
	    public ResponseEntity<ContactReq> getContactsById(@PathVariable int id) {
	    	ContactReq contactReq = experimentService.getContactById(id);

	        // Check if the contact exists
	        if (contactReq!=null) {
	            return ResponseEntity.ok(contactReq);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }

	    

	    @GetMapping("/capturePicture")
	    public ResponseEntity<String> caturePhoto() {

	       return ResponseEntity.ok("success");
	        
	    }
	    
	    
	    @PostMapping("/uploadPicture")
	    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
	        try {
	            File uploadDir = new File(uploadDirPath);
	            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
	                return ResponseEntity.status(500).body("Failed to create upload directory");
	            }

	            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	            System.out.println("FIleName =="+uniqueFileName);
	            File transferFile = new File(uploadDirPath + uniqueFileName);

	            System.out.println("Attempting to transfer file to " + transferFile.getAbsolutePath());
	            file.transferTo(transferFile);
	            System.out.println("File transferred successfully");

	            return ResponseEntity.ok("File uploaded successfully");
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
	        }
	    }
	    
	    
	    private static final String REMOTE_SERVER_URL = "http://10.196.5.19:8080/ECommerceServiceAuth/api/experiment/uploadPicture"; // Replace with the remote server URL
	    //private static final String REMOTE_SERVER_URL = "http://10.240.16.48:8081/ECommerceServiceAuth/v1/image/upload"; // Replace with the remote server URL
	    @PostMapping("/uploadToRemote")
	    public ResponseEntity<String> uploadToRemote(@RequestParam("file") MultipartFile file) {
	        try {
	            // Forward the file to the remote server
	            forwardFileToRemoteServer(file);
	            return ResponseEntity.ok("File uploaded and forwarded successfully");
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload and forward file: " + e.getMessage());
	        }
	    }

	    private void forwardFileToRemoteServer(MultipartFile file) throws IOException {
	        // Prepare headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

	        // Prepare body with the file
	        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	        body.add("file", new MultipartFileResource(file));

	        // Create an HTTP entity with the body and headers
	        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

	        // Send the file to the remote server
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = restTemplate.postForEntity(REMOTE_SERVER_URL, requestEntity, String.class);

	        // Log the response from the remote server
	        System.out.println("Response from remote server: " + response.getBody());
	    }

	    // Helper class to convert MultipartFile to a resource for HTTP transfer
	    public static class MultipartFileResource extends ByteArrayResource {
	        private final MultipartFile file;

	        public MultipartFileResource(MultipartFile file) throws IOException {
	            super(file.getBytes());
	            this.file = file;
	        }

	        @Override
	        public String getFilename() {
	            return file.getOriginalFilename();
	        }
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    


	}
	    
	    