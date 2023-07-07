package com.pms.payment.model;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
	String customerName;
	String email;
	String phoneNumber;
	BigInteger amount;
}
