package com.pms.report.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="SALES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
	@Id
	@Column(name="ID")
private int id;	
	@Column(name="drugNames")
private List<String> drugName;
	@Column(name="dateAndTime")
private Date dateAndTime;
	@Column(name="totalAmt")
private double totalAmt;
	@Column(name="paidAmt")
private double paidAmt;
	@Column(name="balance")
private double Balance;
}
