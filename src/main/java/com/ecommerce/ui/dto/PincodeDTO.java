package com.ecommerce.ui.dto;

import java.util.ArrayList;
import java.util.List;

public class PincodeDTO {
	private String pincode;
	private String district;
	private String state;
	public List<String> locality = new ArrayList<>();
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<String> getLocality() {
		return locality;
	}
	public void setLocality(List<String> locality) {
		this.locality = locality;
	}
	
	
	
}
