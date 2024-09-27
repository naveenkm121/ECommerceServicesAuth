package com.ecommerce.ui.experiment;

import java.util.List;

public class ContactReq {
	
	public String deviceId;
	public List<ContactData> contacts;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public List<ContactData> getContacts() {
		return contacts;
	}
	public void setContacts(List<ContactData> contacts) {
		this.contacts = contacts;
	}
	
	
	
	
	
	
}
