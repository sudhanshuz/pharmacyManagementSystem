package com.pms.orders.exception;

import org.springframework.stereotype.Component;

public class ResourceNotFoundException extends Exception{
	public ResourceNotFoundException (String msg){
	super(msg);
	}
}
