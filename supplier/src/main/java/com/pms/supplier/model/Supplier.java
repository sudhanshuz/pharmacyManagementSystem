package com.pms.supplier.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="Suppliers")
public class Supplier {

	@Id
private int supplierId;
private String supplierName;
private String supplierEmail;
private long supplierPhoneNo;
//private HashMap<Drugs,Integer> stock;
//private Order orders;


public Supplier() {
	super();
	// TODO Auto-generated constructor stub
}


public Supplier(int supplierId, String supplierName, String supplierEmail, long supplierPhoneNo) {
	super();
	this.supplierId = supplierId;
	this.supplierName = supplierName;
	this.supplierEmail = supplierEmail;
	this.supplierPhoneNo = supplierPhoneNo;
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


public long getSupplierPhoneNo() {
	return supplierPhoneNo;
}


public void setSupplierPhoneNo(long supplierPhoneNo) {
	this.supplierPhoneNo = supplierPhoneNo;
}


}
