package com.pms.supplier.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Drugs;
import com.pms.supplier.service.SupplyService;

@RestController
@RequestMapping("/drugs")
public class drugController {
	@Autowired
	private SupplyService serviceObj;
	@GetMapping("/getAll")
	public List<Drugs> viewDrugs(){
		return serviceObj.getDrugs();
	}
	
	@PostMapping("/add")
	public Drugs addDrugs(@RequestBody @Valid Drugs drug) {
		return serviceObj.insertDrug(drug);
	}
	
	@GetMapping("/viewDrugByName/{name}")
	public Drugs viewDrugByName(@PathVariable String name) throws ResourceNotFoundException {
		return serviceObj.viewDrugByName(name);
	}
	
	@GetMapping("/getDrugPrice/{drugName}")
	public double getDrugPrice(@PathVariable String drugName) throws ResourceNotFoundException {
		return serviceObj.getDrugPrice(drugName);
	}
	@DeleteMapping("/deleteDrugByName/{drugName}")
	public Drugs deleteDrugs(@PathVariable String drugName) throws ResourceNotFoundException {
		return serviceObj.deleteDrugs( drugName);
	}
	@PutMapping("/edit")
	public Drugs editDrugs(@RequestBody Drugs drug) {
		return serviceObj.editDrugs(drug);
	}
}
