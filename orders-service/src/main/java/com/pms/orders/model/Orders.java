package com.pms.orders.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="Orders")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	@Transient
	public static final String SEQUENCE_NAME="orders_sequence";
	@Id
	private Long orderId;//auto added
	@NotEmpty(message="cannot be blank")
	private String docName;
	@NotEmpty(message="cannot be blank")
	private long docContact;
	@NotEmpty(message="cannot be blank")
	private String docEmail;
	private double total; //auto added
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private String pickupDate;//auto added
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
	
	
	
	//private String supplierName
	
	
	
	
	
	
}
