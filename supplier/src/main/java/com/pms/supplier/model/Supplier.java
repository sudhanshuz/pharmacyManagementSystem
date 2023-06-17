package com.pms.supplier.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="Suppliers")
public class Supplier {
	@Id
private int supplierId;
private String supplierName;
private String supplierEmail;
private long supplierPhoneNo;
private List<HashMap<String,Integer>>stock=new ArrayList<>();
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
public List<HashMap<String, Integer>> getStock() {
	return stock;
}
public void setStock(List<HashMap<String, Integer>> stock) {
	this.stock = stock;
}
public Supplier(int supplierId, String supplierName, String supplierEmail, long supplierPhoneNo,
		List<HashMap<String, Integer>> stock) {
	super();
	this.supplierId = supplierId;
	this.supplierName = supplierName;
	this.supplierEmail = supplierEmail;
	this.supplierPhoneNo = supplierPhoneNo;
	this.stock = stock;
}
public Supplier() {
	super();
}




}
