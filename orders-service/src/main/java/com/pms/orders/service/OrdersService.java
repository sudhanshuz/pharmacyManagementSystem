package com.pms.orders.service;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.orders.exception.ResourceNotFoundException;
import com.pms.orders.model.Orders;
import com.pms.orders.model.PickedUpOrders;
import com.pms.orders.repository.OrdersRepository;
import com.pms.orders.repository.PickedUpOrdersRepo;


@Service
public class OrdersService {
	@Autowired
	OrdersRepository orderRepository;
	@Autowired
PickedUpOrdersRepo pickedUpOrdersRepo;
	@Autowired
	RestTemplate restTemplate;
	public Orders addOrder(Orders order) {
		HashMap<String,Integer> drugInfo=order.getDrugInfo();
		double total=0;
        Iterator<Map.Entry<String, Integer>> itr = drugInfo.entrySet().iterator();
        
        while(itr.hasNext())
        {
             Map.Entry<String, Integer> entry = itr.next();
             String drugName=entry.getKey();
             int qty=entry.getValue();
     		double price=restTemplate.getForObject("http://SUPPLIER-SERVICE/drugs/getDrugPrice/"+drugName,Double.class);
     		total=total+(price*qty);
        }
        order.setTotal(total);
		Date pickupDate=restTemplate.getForObject("http://SUPPLIER-SERVICE/supplier/setPickupDate/"+order.getOrderId(),Date.class);
		order.setPickupDate(pickupDate);
        
	return orderRepository.insert(order);	
	}

	public List<Orders> getOrders() {
		return orderRepository.findAll();
	}

	public Orders getOrdersDetailsById(long orderId) {		
		return orderRepository.findById(orderId).orElse(null);
	}

	public List<Orders> getOrdersByDocName(String docName) {
		return orderRepository.findByDocName(docName);
	}

	public Orders setTotalPrice(String drugName, int qty,long orderId) throws ResourceNotFoundException {
		double price=restTemplate.getForObject("http//SUPPLIER-SERVICE/drugs//getDrugPrice/"+drugName,Double.class);
		Orders order=orderRepository.findById(orderId).orElse(null);
		if(order==null) {
			throw new ResourceNotFoundException("invalid order id");
		}
		order.setTotal(price*qty);
		return orderRepository.save(order);
	}

	public Orders setPickupDate(long orderId) throws ResourceNotFoundException {
		Orders order=orderRepository.findById(orderId).orElse(null);
		if(order==null) {
			throw new ResourceNotFoundException("invalid order id");
		}
		Date pickupDate=restTemplate.getForObject("http//SUPPLIER-SERVICE/setPickupDate/"+orderId,Date.class);
		order.setPickupDate(pickupDate);
		return orderRepository.save(order);
	}

	public Orders editOrder(Orders order) {
		return orderRepository.save(order);
	}

	public Orders deleteOrder(long orderId) {
		orderRepository.deleteById(orderId);
		return orderRepository.findById(orderId).orElse(null);
	}

	
	
	
	
}
