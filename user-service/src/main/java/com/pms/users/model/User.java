package com.pms.users.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="user")
public class User {
	@Transient
	public static final String SEQUENCE_NAME="users_sequence";
	@Id
	private long userId;
	private String name;
	private long contact;
	private String email;
	private String password;
	private String role;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	public User(String name, long contact, String email, String password, String role) {
		super();
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public User() {
		super();
	}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	
}
