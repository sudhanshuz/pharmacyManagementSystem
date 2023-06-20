package com.pms.orders.model;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private Date pickupDate;//auto added
	private HashMap<String,Integer> drugInfo;
}
