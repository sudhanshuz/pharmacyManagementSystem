package com.pms.users.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


@Document(collection="user")
public class User {
	@Transient
	public static final String SEQUENCE_NAME="users_sequence";
	@Id
	private int userId;
	@NotEmpty(message="username should'nt be blank")
	//name is username here
	private String name;
	@NotEmpty(message="name should'nt be blank")
	private String fullName;
	@Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
	private String contact;
	@Email(message="invalid email address")
	private String email;
	@NotEmpty(message="password should'nt be blank")
	private String password;
	private String role="ROLE_DOCTOR";
	public User(String name, String contact, String email, String password) {
		super();
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.password = password;
	}
	
	
	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public long getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
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
	public User(String name, String contact, String email, String password, String role) {
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

	public User(int userId, @NotEmpty(message = "username should'nt be blank") String name,
			@NotEmpty(message = "name should'nt be blank") String fullName,
			@Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ") String contact,
			@Email(message = "invalid email address") String email,
			@NotEmpty(message = "password should'nt be blank") String password, String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.fullName = fullName;
		this.contact = contact;
		this.email = email;
		this.password = password;
	}


	public User(int userId, @NotEmpty(message = "username should'nt be blank") String name,
			@NotEmpty(message = "name should'nt be blank") String fullName,
			@Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ") String contact,
			@Email(message = "invalid email address") String email,
			@NotEmpty(message = "password should'nt be blank") String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.fullName = fullName;
		this.contact = contact;
		this.email = email;
		this.password = password;
	}

	public User(int userId, @NotEmpty(message = "username should'nt be blank") String name,
			@NotEmpty(message = "name should'nt be blank") String fullName,
			@Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ") String contact,
			@Email(message = "invalid email address") String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.fullName = fullName;
		this.contact = contact;
		this.email = email;
	}

	
	
	
}
