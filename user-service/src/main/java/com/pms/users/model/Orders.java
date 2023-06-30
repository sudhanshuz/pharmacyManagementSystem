package com.pms.users.model;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="OrdersCopy")
@ToString
public class Orders {
	@Id
	private Long orderId;//auto added
	@NotEmpty(message="name cannot be blank")
	//(doctors username)
	private String docName;
	@Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
	private String docContact;
	@Email(message="invalid email")
	private String docEmail;
	private double total; //auto added
	private Date pickupDate;//auto added
	private HashMap<String,Integer> drugInfo;
	private boolean verified=false;
	private boolean pickedUp=false;
	
	private int supplierId;
	
	
	
}
