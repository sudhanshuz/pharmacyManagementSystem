package com.pms.report.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	private int salesId;
	@Column(name="drugNames")
private List<String> drugName;
	@Column(name="dateAndTime")
private String dateAndTime;
	@Column(name="totalAmt")
private double totalAmt;
	@Column(name="paidAmt")
private double paidAmt;
	@Column(name="balance")
private double Balance;
}
