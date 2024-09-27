package com.ecommerce.ui.experiment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "device_id")
    private String deviceId;

    @Column(name="contact_data",columnDefinition = "TEXT")  // Storing JSON as Text
    private String contactData;  // Store JSON data as a String

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getContactData() {
		return contactData;
	}

	public void setContactData(String contactData) {
		this.contactData = contactData;
	}

    // Getters and Setters
    
    
    
}