package com.ecommerce.ui.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class UserDTO {
	
    private Long id;
	private String name;
	private String email;
	private String mobile;
	private String role;
	private String image;
	private Date dob;
	private Date createdDate;
	private Date updatedDate;
	
	
	
	
	
	public UserDTO() {
		super();
	}
	
	
	
	public UserDTO(Long id, String name, String email, String mobile, String role,String image, Date dob, Date createdDate,
			Date updatedDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
		this.image=image;
		this.dob = dob;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRole() {
		return role;
	}
	
	
	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public void setRole(String role) {
		this.role = role;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}



	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", role=" + role
				+ ", dob=" + dob + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
	
	
	
	

}
