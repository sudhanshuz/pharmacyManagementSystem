package com.pms.supplier.model;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
@Document(collection="drugs")
public class Drugs {
	@Id
	@NotEmpty(message="please specify drug name")
private String drugName;
	@NotNull(message="pleaase enter price")
private double price;
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
private Date exp_date;
@NotEmpty(message="please provide batch id")
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
}
@Override
public int hashCode() {
	return Objects.hash(batchId, drugName, exp_date, price);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Drugs other = (Drugs) obj;
	return Objects.equals(batchId, other.batchId) && Objects.equals(drugName, other.drugName)
			&& Objects.equals(exp_date, other.exp_date)
			&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
}
@Override
public String toString() {
	return "Drugs [drugName=" + drugName + ", price=" + price + ", exp_date=" + exp_date + ", batchId=" + batchId + "]";
}



}
