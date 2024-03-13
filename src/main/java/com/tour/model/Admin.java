package com.tour.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Admin")
public class Admin {
	
	@Id
	String id;
	String name;
	String mobile;
	String emailId;
	String password;
	public Admin(String id, String name, String mobile, String emailId, String password) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.emailId = emailId;
		this.password = password;
	}
	public Admin(String password) {
		super();
		this.password = password;
	}
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", mobile=" + mobile + ", emailId=" + emailId + ", password="
				+ password + "]";
	}
	
}
