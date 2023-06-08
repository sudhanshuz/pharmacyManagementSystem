package com.pms.supplier.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
@Document(collection="drugs")
public class Drugs {
	@Id
private String drugName;
private double price;
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
private Date exp_date;
private String batchId;
public String getDrugName() {
	return drugName;
}
public void setDrugName(String drugName) {
	this.drugName = drugName;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public Date getExp_date() {
	return exp_date;
}
public void setExp_date(Date exp_date) {
	this.exp_date = exp_date;
}
public String getBatchId() {
	return batchId;
}
public void setBatchId(String batchId) {
	this.batchId = batchId;
}
public Drugs(String drugName, double price, Date exp_date, String batchId) {
	super();
	this.drugName = drugName;
	this.price = price;
	this.exp_date = exp_date;
	this.batchId = batchId;
}
public Drugs() {
	super();
	// TODO Auto-generated constructor stub
}

}
