package com.pms.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.report.model.Sales;
import com.pms.report.repository.salesRepository;

@RestController
@RequestMapping("/salesReport")
public class SalesController {
	
	@Autowired
	private salesRepository salesRepo;

	@PostMapping("/add")
	public Sales addSalesReport(@RequestBody Sales sales) {
		return salesRepo.save(sales);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteSalesReport(@PathVariable String id) {
		int salesId=Integer.parseInt(id);
		salesRepo.deleteById(salesId);
		return "deleted successfully";
	}
	@GetMapping("/getAll")
	public List<Sales> getSales(){
		return salesRepo.findAll();
	}
}
