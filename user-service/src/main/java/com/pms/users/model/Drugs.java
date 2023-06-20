package com.pms.users.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="drugsCopy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drugs {
	@Id
	private String drugName;
	private double price;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date exp_date;
	private String batchId;
}
