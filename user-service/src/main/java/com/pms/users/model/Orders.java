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
	@NotEmpty(message="cannot be blank")
	private String docName;
	@NotEmpty(message="cannot be blank")
	private long docContact;
	@NotEmpty(message="cannot be blank")
	private String docEmail;
	private double total; //auto added
	private Date pickupDate;//auto added
	@NotEmpty(message="cannot be blank")
	private String drugName;
	private int qty;
	private boolean verified=false;
	private boolean pickedUp=false;
	private int supplierId;
	public Orders(@NotEmpty(message = "cannot be blank") String docName,
			@NotEmpty(message = "cannot be blank") long docContact,
			@NotEmpty(message = "cannot be blank") String docEmail, String drugName, int qty) {
		super();
		this.docName = docName;
		this.docContact = docContact;
		this.docEmail = docEmail;
		this.drugName = drugName;
		this.qty = qty;
	}
	
	
	
}
