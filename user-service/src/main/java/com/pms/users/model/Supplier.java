package com.pms.users.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="suppliersCopy")
public class Supplier {
	@Id
	private int supplierId;
	private String supplierName;
	private String supplierEmail;
	private String supplierPhoneNo;
	private List<HashMap<String,Integer>>stock=new ArrayList<>();
	public Supplier(int supplierId, String supplierName, String supplierEmail, String supplierPhoneNo,
			List<HashMap<String, Integer>> stock) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierEmail = supplierEmail;
		this.supplierPhoneNo = supplierPhoneNo;
		this.stock = stock;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierEmail() {
		return supplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}
	public String getSupplierPhoneNo() {
		return supplierPhoneNo;
	}
	public void setSupplierPhoneNo(String supplierPhoneNo) {
		this.supplierPhoneNo = supplierPhoneNo;
	}
	public Supplier(int supplierId, String supplierName, String supplierEmail, String supplierPhoneNo) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierEmail = supplierEmail;
		this.supplierPhoneNo = supplierPhoneNo;
	}
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
