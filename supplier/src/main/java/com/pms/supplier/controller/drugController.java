package com.pms.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.supplier.model.Drugs;
import com.pms.supplier.service.service;

@RestController
public class drugController {
	@Autowired
	private service serviceObj;
	@GetMapping("/getDrugs")
	public List<Drugs> viewDrugs(){
		return serviceObj.getDrugs();
	}
	
	@PostMapping("/addDrug")
	public Drugs addDrugs(@RequestBody Drugs drug) {
		return serviceObj.insertDrug(drug);
	}
}
