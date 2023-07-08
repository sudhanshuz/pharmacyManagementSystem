package com.pms.orders.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pms.orders.exception.ResourceNotFoundException;
import com.pms.orders.model.NewOrders;
import com.pms.orders.model.Orders;
import com.pms.orders.model.PickedUpOrders;
import com.pms.orders.model.VerifiedOrders;
import com.pms.orders.service.OrdersService;
import com.pms.orders.service.SequenceGeneratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private SequenceGeneratorService service;
	
	Logger logger=LoggerFactory.getLogger(OrdersController.class);
	
	@PostMapping("/add")
	public Orders save(@RequestBody @Valid Orders order) {
		order.setOrderId(service.generateSequence(Orders.SEQUENCE_NAME));
		logger.info("added");
		
		return ordersService.addOrder(order);
	}
	
	@GetMapping("/getAll")
	public List<Orders> getOrders(){
		return ordersService.getOrders();
	}
	
	@GetMapping("/getOrdersById/{orderId}")
	public Orders getOrdersDetailsById(@PathVariable long orderId) {
		return ordersService.getOrdersDetailsById(orderId);
	}
	
	@GetMapping("/getOrdersByDocName/{docName}")
	public List<Orders> getOrdersByDocName(@PathVariable String docName){
		return ordersService.getOrdersByDocName(docName);
	}
	@PutMapping("/setTotalPrice/{docName}/{qty}")
	public Orders setTotalPrice(@PathVariable String drugName,@PathVariable String qty,long orderId) throws NumberFormatException, ResourceNotFoundException {
		//connect supplier ms to get total price
		return ordersService.setTotalPrice(drugName,Integer.parseInt(qty),orderId);
	}
	
	@PutMapping("/setPickupDate/{orderId}")
	public Orders setPickupDate(long orderId) throws ResourceNotFoundException {
		return ordersService.setPickupDate(orderId);
	}
	
	@PostMapping("/edit")
	public Orders editOrder(@RequestBody @Valid Orders order) {
		return ordersService.editOrder(order);
	}
	
	@DeleteMapping("/delete/{orderId}")
	public Orders deleteOrder(@PathVariable String orderId) {
		return ordersService.deleteOrder(Long.parseLong(orderId));
	}
	
	@PostMapping("/addVerifiedOrders")
	public VerifiedOrders addVerifiedOrders(@RequestBody Orders order ) {
		return ordersService.addVerifiedOrders(order);
	}
	
	@PostMapping("/addPickedUpOrders")
	public Orders addPickedUpOrders(@RequestBody Orders order) {
		return ordersService.addPickedUpOrders(order);
	}
	
	@GetMapping("/viewNewOrders")
	public List<NewOrders> viewNewOrders() {
		return ordersService.viewNewOrders();
	}
	
	@GetMapping("viewVerifiedOrders")
	public List<VerifiedOrders> viewVerifiedOrders(){
		return ordersService.viewVerifiedOrders();
	}
	
	@GetMapping("viewPickedUpOrders")
	public List<PickedUpOrders> viewPickedUpOrders(){
		return ordersService.viewPickedUpOrders();
	}
	
	@GetMapping("/getMyOrdersByDocName/{docName}")
	public List<PickedUpOrders> getMyOrdersByDocName(@PathVariable String docName){
		return ordersService.getMyOrdersByDocName(docName);
	}
	
	@GetMapping("/getOrdersByPickUpDate/{pickUpDate}")
	public List<PickedUpOrders> getOrdersByPickUpDate(@PathVariable String pickUpDate){
		System.out.println(pickUpDate);
		return ordersService.getOrdersByPickUpDate(pickUpDate);
	}
}
