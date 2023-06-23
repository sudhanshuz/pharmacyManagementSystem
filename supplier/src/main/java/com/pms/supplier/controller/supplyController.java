package com.pms.supplier.controller;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Orders;
import com.pms.supplier.model.Supplier;
import com.pms.supplier.service.SupplyService;

@RestController
@RequestMapping("/supplier")
public class supplyController {
	@Autowired
	private SupplyService serviceObj;
	@Autowired
	private RestTemplate restTemplate;
	
	Logger logger=LoggerFactory.getLogger(supplyController.class);
	
	@GetMapping("/getAll")
	public List<Supplier> getSuppliers() {
		return serviceObj.viewSuppliers();
	}
	
	@PostMapping("/add")
	public Supplier addSuppliers(@RequestBody Supplier supplier) {
		return serviceObj.insertSuppliers(supplier);
	}
	
	@PutMapping("/load")
	public Supplier loadStock(@RequestParam int id,@RequestParam String drugName,@RequestParam int qty) throws ResourceNotFoundException{
		return serviceObj.addStock(id,drugName,qty);
	}
	@GetMapping("/setPickupDate/{orderId}")
	public Date setPickupDate(@PathVariable String orderId) {
		//verify order if everything is ok set pickup date
	    return new Date(new Date().getTime() + (86400000*2));
	}
	@DeleteMapping("/delete/{supplierId}")
	public Supplier deleteSupplier(@PathVariable String supplierId ) {
		int id=Integer.parseInt(supplierId);
		return serviceObj.deleteSupplier(id );
	}
	
	@PutMapping("/edit")
	public Supplier editSupplier(@RequestBody Supplier supplier) {
		return serviceObj.editSupplier(supplier);
	}
	@GetMapping("/viewAvailableOrders")
	public Orders[] viewAvailableOrders() {
		return serviceObj.serviceObj();
	}
	
	@PutMapping("/pickUpOrder/{orderId}/{supplierId}")
	public String pickUpOrder(@PathVariable String orderId,@PathVariable String supplierId) {
		return serviceObj.pickUpOrder(Long.parseLong(orderId),Integer.parseInt(supplierId));
	}
	
	@GetMapping("/findMyOrders/{supplierId}")
	public List<Orders> findMyOrders(@PathVariable String supplierId) {
		return serviceObj.findMyOrders(Integer.parseInt(supplierId));
	}
}
