package com.pms.orders.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.pms.orders.service.SequenceGeneratorService;

@Document(collection="Orders")
@Component
public class Orders {
	@Transient
	public static final String SEQUENCE_NAME="orders_sequence";
	@Id
	private Long orderId;
	
	private String docName;
	private long docContact;
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
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
