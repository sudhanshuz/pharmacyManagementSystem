package com.pms.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.supplier.model.Supplier;
import com.pms.supplier.service.service;

@RestController
public class supplyController {
	@Autowired
	private service serviceObj;
	@GetMapping("/get")
	public List<Supplier> getSuppliers() {
		return serviceObj.viewSuppliers();
	}
	
	@PostMapping("/add")
	public Supplier addSuppliers(@RequestBody Supplier supplier) {
		return serviceObj.insertSuppliers(supplier);
	}
}
