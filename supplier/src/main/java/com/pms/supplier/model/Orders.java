package com.pms.supplier.model;

import java.util.Date;
import java.util.HashMap;

import javax.validation.constraints.NotEmpty;

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
}
