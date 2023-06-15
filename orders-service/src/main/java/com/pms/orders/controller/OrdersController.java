package com.pms.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.orders.model.Orders;
import com.pms.orders.repository.OrdersRepository;
import com.pms.orders.service.OrdersService;
import com.pms.orders.service.SequenceGeneratorService;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private SequenceGeneratorService service;
	
	@PostMapping("/addOrder")
	public Orders save(@RequestBody Orders order) {
		order.setOrderId(service.generateSequence(Orders.SEQUENCE_NAME));
		return ordersService.addOrder(order);
	}
	
	@GetMapping("/getAllorders")
	public List<Orders> getOrders(){
		return ordersService.getOrders();
	}
	
	@GetMapping("/getOrdersById")
	public Orders getOrdersDetailsById(long orderId) {
		return ordersService.getOrdersDetailsById(orderId);
	}
	
	@GetMapping("/getOrdersByDocName/{docName}")
	public List<Orders> getOrdersByDocName(@PathVariable String docName){
		return ordersService.getOrdersByDocName(docName);
	}
	@PutMapping("/setTotalPrice/{docName}/{qty}")
	public Orders setTotalPrice(@PathVariable String drugName,@PathVariable String qty,long orderId) {
		//connect supplier ms to get total price
		return ordersService.setTotalPrice(drugName,Integer.parseInt(qty),orderId);
	}
	
	@PutMapping("/setPickupDate/{orderId}")
	public Orders setPickupDate(long orderId) {
		return ordersService.setPickupDate(orderId);
	}
}
