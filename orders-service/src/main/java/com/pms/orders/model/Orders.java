package com.pms.orders.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;

@Document(collection="Orders")
@Component
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
	private Date pickupDate;//auto added
	private HashMap<String,Integer> drugInfo;
	private boolean verified=false;
	private boolean pickedUp=false;
	private int supplierId;
	
	
	
	//private String supplierName
	
	
	
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public Orders(Long orderId, @NotEmpty(message = "cannot be blank") String docName,
			@NotEmpty(message = "cannot be blank") long docContact,
			@NotEmpty(message = "cannot be blank") String docEmail, double total, Date pickupDate,
			HashMap<String, Integer> drugInfo, boolean verified, boolean pickedUp) {
		super();
		this.orderId = orderId;
		this.docName = docName;
		this.docContact = docContact;
		this.docEmail = docEmail;
		this.total = total;
		this.pickupDate = pickupDate;
		this.drugInfo = drugInfo;
		this.verified = verified;
		this.pickedUp = pickedUp;
	}
	public boolean isVerified() {
		return verified;
	}
	public Orders(Long orderId, @NotEmpty(message = "cannot be blank") String docName,
			@NotEmpty(message = "cannot be blank") long docContact,
			@NotEmpty(message = "cannot be blank") String docEmail, double total, Date pickupDate,
			HashMap<String, Integer> drugInfo) {
		super();
		this.orderId = orderId;
		this.docName = docName;
		this.docContact = docContact;
		this.docEmail = docEmail;
		this.total = total;
		this.pickupDate = pickupDate;
		this.drugInfo = drugInfo;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public boolean isPickedUp() {
		return pickedUp;
	}
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
	
	public HashMap<String, Integer> getDrugInfo() {
		return drugInfo;
	}
	public void setDrugInfo(HashMap<String, Integer> drugInfo) {
		this.drugInfo = drugInfo;
	}
	public String getDocEmail() {
		return docEmail;
	}
	public void setDocEmail(String docEmail) {
		this.docEmail = docEmail;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public long getDocContact() {
		return docContact;
	}
	public void setDocContact(long docContact) {
		this.docContact = docContact;
	}
	public Orders(Long orderId, String docName, long docContact) {
		super();
		this.orderId = orderId;
		this.docName = docName;
		this.docContact = docContact;
	}
	
	public Orders(String docName, long docContact, String docEmail, HashMap<String, Integer> drugInfo) {
		super();
		this.docName = docName;
		this.docContact = docContact;
		this.docEmail = docEmail;
		this.drugInfo = drugInfo;
	}
	public Orders() {
		super();
	}
	
	
}
