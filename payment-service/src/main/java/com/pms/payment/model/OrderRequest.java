package com.pms.payment.model;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderRequest {
	String customerName;
	String email;
	String phoneNumber;
	BigInteger amount;
}
