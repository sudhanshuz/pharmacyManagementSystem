package com.pms.supplier.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pms.supplier.model.Drugs;
import com.pms.supplier.model.Supplier;
import com.pms.supplier.service.SupplyService;

@RestController
public class supplyController {
	@Autowired
	private SupplyService serviceObj;
	@GetMapping("/get")
	public List<Supplier> getSuppliers() {
		return serviceObj.viewSuppliers();
	}
	
	@PostMapping("/add")
	public Supplier addSuppliers(@RequestBody Supplier supplier) {
		return serviceObj.insertSuppliers(supplier);
	}
	
	@PutMapping("/load")
	public Supplier loadStock(@RequestParam int id,@RequestParam String drugName,@RequestParam int qty){
		return serviceObj.addStock(id,drugName,qty);
	}
}
