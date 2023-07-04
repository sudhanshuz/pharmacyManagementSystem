package com.pms.supplier.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Drugs;
import com.pms.supplier.service.SupplyService;

@RestController
@RequestMapping("/drugs")
@CrossOrigin("*")
public class drugController {
	
	Logger logger=LoggerFactory.getLogger(drugController.class);
	@Autowired
	private SupplyService serviceObj;
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@GetMapping("/getAll")
	public List<Drugs> viewDrugs(){
		return serviceObj.getDrugs();
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@PostMapping("/add")
	public Drugs addDrugs(@RequestBody @Valid Drugs drug) {
		logger.info("check");
		logger.error("what's wrong");
		return serviceObj.insertDrug(drug);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@GetMapping("/viewDrugByName/{name}")
	public Drugs viewDrugByName(@PathVariable String name) throws ResourceNotFoundException {
		return serviceObj.viewDrugByName(name);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@GetMapping("/getDrugPrice/{drugName}")
	public double getDrugPrice(@PathVariable String drugName) throws ResourceNotFoundException {
		return serviceObj.getDrugPrice(drugName);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@DeleteMapping("/deleteDrugByName/{drugName}")
	public Drugs deleteDrugs(@PathVariable String drugName) throws ResourceNotFoundException {
		return serviceObj.deleteDrugs( drugName);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@PutMapping("/edit")
	public Drugs editDrugs(@RequestBody Drugs drug) {
		return serviceObj.editDrugs(drug);
	}
}
