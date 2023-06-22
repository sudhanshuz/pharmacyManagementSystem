package com.pms.orders.model;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="NewOrders")
public class NewOrders {

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
	private HashMap<String,Integer> drugInfo;
	private boolean verified=false;
	private boolean pickedUp=false;
	public NewOrders(Long orderId, String docName, long docContact,String docEmail, double total, Date pickupDate,HashMap<String, Integer> drugInfo) {
		super();
		this.orderId = orderId;
		this.docName = docName;
		this.docContact = docContact;
		this.docEmail = docEmail;
		this.total = total;
		this.pickupDate = pickupDate;
		this.drugInfo = drugInfo;
	}
	

}
