package com.pms.supplier.model;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Suppliers")
public class Supplier {
	@Id
	@NotNull(message="Id cannot be blank")
private int supplierId;
	@NotEmpty(message="supplier Name cannot be blank")
private String supplierName;
	@Email(message="invalid email")
private String supplierEmail;
	@Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
private String supplierPhoneNo;
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
public String getSupplierPhoneNo() {
	return supplierPhoneNo;
}
public void setSupplierPhoneNo(String supplierPhoneNo) {
	this.supplierPhoneNo = supplierPhoneNo;
}
public List<HashMap<String, Integer>> getStock() {
	return stock;
}
public void setStock(List<HashMap<String, Integer>> stock) {
	this.stock = stock;
}
public Supplier(int supplierId, String supplierName, String supplierEmail, String supplierPhoneNo,
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
