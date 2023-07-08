package com.pms.orders.model;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="verifiedOrders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifiedOrders {
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
	private String drugName;
	private int qty;
	private boolean verified=false;
	private boolean pickedUp=false;
	private int supplierId;
	public VerifiedOrders(Long orderId, @NotEmpty(message = "cannot be blank") String docName,
			@NotEmpty(message = "cannot be blank") long docContact,
			@NotEmpty(message = "cannot be blank") String docEmail, double total, String pickupDate, String drugName,
			int qty, boolean verified, boolean pickedUp) {
		super();
		this.orderId = orderId;
		this.docName = docName;
		this.docContact = docContact;
		this.docEmail = docEmail;
		this.total = total;
		this.pickupDate = pickupDate;
		this.drugName = drugName;
		this.qty = qty;
		this.verified = verified;
		this.pickedUp = pickedUp;
	}
}
