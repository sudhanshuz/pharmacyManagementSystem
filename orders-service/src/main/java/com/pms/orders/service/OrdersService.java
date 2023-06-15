package com.pms.orders.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.orders.model.Orders;
import com.pms.orders.repository.OrdersRepository;

@Service
public class OrdersService {
	@Autowired
	OrdersRepository orderRepository;
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
     		double price=restTemplate.getForObject("http://localhost:8000/drugs/getDrugPrice/"+drugName,Double.class);
     		total=total+(price*qty);
        }
        order.setTotal(total);
		Date pickupDate=restTemplate.getForObject("http://localhost:8000/supplier/setPickupDate/"+order.getOrderId(),Date.class);
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

	public Orders setTotalPrice(String drugName, int qty,long orderId) {
		double price=restTemplate.getForObject("localhost:8000/drugs//getDrugPrice/{drugName}",Double.class);
		Orders order=orderRepository.findById(orderId).orElse(null);
		order.setTotal(price*qty);
		return orderRepository.save(order);
	}

	public Orders setPickupDate(long orderId) {
		// TODO Auto-generated method stub
		Orders order=orderRepository.findById(orderId).orElse(null);
		Date pickupDate=restTemplate.getForObject("http//localhost:8000/setPickupDate/"+orderId,Date.class);
		order.setPickupDate(pickupDate);
		return orderRepository.save(order);
	}
	
	
}
