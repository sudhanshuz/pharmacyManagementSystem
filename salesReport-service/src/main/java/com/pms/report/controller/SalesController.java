package com.pms.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.report.model.Sales;
import com.pms.report.repository.salesRepository;
import com.pms.report.service.SalesService;

@RestController
@RequestMapping("/salesReport")
@CrossOrigin("*")
public class SalesController {
	
@Autowired	
SalesService salesService;
	
/* Author: Sudhanshu
Modified By:Sudhanshu
Modified Time:
description: add all the sales reports into the database
*/
	@PostMapping("/add")
	public Sales addSalesReport(@RequestBody Sales sales) {
		return salesService.addReport(sales);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description: delete the sales report by it's id
	 */	
	@DeleteMapping("/delete/{id}")
	public boolean deleteSalesReport(@PathVariable int id) {
		return salesService.deleteSalesReport(id);
	}
	
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description: add todays sales reports
	 */
	@GetMapping("/addSalesByDate/{date}")
	public Sales addSales(@PathVariable String date){
		return salesService.addReportsByDate(date);
	}
	
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:get all the sales reports
	 */
	@GetMapping("/getReports")
	public List<Sales> getReports() {
	return salesService.getReports();	
	}
}
