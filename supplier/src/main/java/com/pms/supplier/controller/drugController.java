package com.pms.supplier.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Drugs;
import com.pms.supplier.model.Orders;
import com.pms.supplier.service.SupplyService;

@RestController
@RequestMapping("/drugs")
@CrossOrigin("*")
public class drugController {
	
	Logger logger=LoggerFactory.getLogger(drugController.class);
	@Autowired
	private SupplyService serviceObj;
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper mapper;
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description: get all the drugs from the repository
	 */
	@GetMapping("/getAll")
	public List<Drugs> viewDrugs(){
		return serviceObj.getDrugs();
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:add the drugs to the repository
	 */
	@PostMapping("/add")
	public Drugs addDrugs(@RequestBody @Valid Drugs drug) {
		logger.info("check");
		logger.error("what's wrong");
		return serviceObj.insertDrug(drug);
	}
	//*************************************
	@PostMapping("/addWithImg")
	public ResponseEntity<?> addDrugsWithImg(@RequestParam("file") MultipartFile file,
								@RequestParam("drugData") String drug) throws JsonMappingException, JsonProcessingException {
		logger.info("check");
		logger.error("what's wrong");
		Drugs drugs=mapper.readValue(drug,Drugs.class);
		serviceObj.insertDrug(drugs);
		
		return ResponseEntity.ok(drugs);
	}
	
	//******************************************
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description: view drugs by its name
	 */
	@GetMapping("/viewDrugByName/{name}")
	public Drugs viewDrugByName(@PathVariable String name) throws ResourceNotFoundException {
		return serviceObj.viewDrugByName(name);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description: get the drug price
	 */
	@GetMapping("/getDrugPrice/{drugName}")
	public double getDrugPrice(@PathVariable String drugName) throws ResourceNotFoundException {
		return serviceObj.getDrugPrice(drugName);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description: delete drug by its name
	 */
	@DeleteMapping("/deleteDrugByName/{drugName}")
	public Drugs deleteDrugs(@PathVariable String drugName) throws ResourceNotFoundException {
		return serviceObj.deleteDrugs( drugName);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description: edit the requested drug
	 */
	@PutMapping("/edit")
	public Drugs editDrugs(@RequestBody Drugs drug) {
		return serviceObj.editDrugs(drug);
	}
}
