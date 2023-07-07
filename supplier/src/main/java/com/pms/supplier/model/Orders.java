package com.pms.supplier.model;

import java.util.Date;
import java.util.HashMap;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

	@Id
	private Long orderId;//auto added
	@NotEmpty(message="name cannot be blank")
	private String docName;
	@Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
	private String docContact;
	@Email(message="Invalid email")
	private String docEmail;
	private double total; //auto added
	private Date pickupDate;//auto added
	private String drugName;
	private int qty;
	private boolean verified=false;
	private boolean pickedUp=false;
	private int supplierId;
}
