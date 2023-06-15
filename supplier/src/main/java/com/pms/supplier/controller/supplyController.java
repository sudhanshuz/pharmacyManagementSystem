package com.pms.supplier.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/get")
	public List<Supplier> getSuppliers() {
		return serviceObj.viewSuppliers();
	}
	
	@PostMapping("/add")
	public Supplier addSuppliers(@RequestBody Supplier supplier) throws ResourceNotFoundException {
		return serviceObj.insertSuppliers(supplier);
	}
	
	@PutMapping("/load")
	public Supplier loadStock(@RequestParam int id,@RequestParam String drugName,@RequestParam int qty) throws ResourceNotFoundException{
		return serviceObj.addStock(id,drugName,qty);
	}
	@GetMapping("/setPickupDate/{orderId}")
	public Date setPickupDate(@PathVariable String orderId) {
		//verify order if everything is ok set pickup date
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date(new Date().getTime() + (86400000*2)); 
		return date;
	}
}
