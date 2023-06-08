package com.pms.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.supplier.model.Drugs;
import com.pms.supplier.service.SupplyService;

@RestController
public class drugController {
	@Autowired
	private SupplyService serviceObj;
	@GetMapping("/getDrugs")
	public List<Drugs> viewDrugs(){
		return serviceObj.getDrugs();
	}
	
	@PostMapping("/addDrug")
	public Drugs addDrugs(@RequestBody Drugs drug) {
		return serviceObj.insertDrug(drug);
	}
	
	@GetMapping("/viewDrugByName/{name}")
	public Drugs viewDrugByName(@PathVariable String name) {
		return serviceObj.viewDrugByName(name);
	}
}
