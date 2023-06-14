package com.pms.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.orders.model.Orders;
import com.pms.orders.repository.OrdersRepository;
import com.pms.orders.service.SequenceGeneratorService;

@RestController
public class OrdersController {
	@Autowired
	private OrdersRepository repo;
	@Autowired
	private SequenceGeneratorService service;
	
	@PostMapping("/addOrder")
	
	public Orders save(@RequestBody Orders order) {
		order.setOrderId(service.generateSequence(Orders.SEQUENCE_NAME));
		return repo.save(order);
	}
	
	@GetMapping("/orders")
	public List<Orders> getOrders(){
		return repo.findAll();
	}
}
