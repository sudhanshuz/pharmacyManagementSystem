package com.pms.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.report.model.PickedUpOrders;
import com.pms.report.model.Sales;
import com.pms.report.repository.salesRepository;

@Service
public class SalesService {

	@Autowired
	private salesRepository salesRepo;
	
	@Autowired
	private RestTemplate  restTemplate;

	public Sales addReport(Sales sales) {
		
		return salesRepo.save(sales);
	}

	public Sales addReportsByDate(String date) {
		PickedUpOrders[] order=restTemplate.getForObject("http://ORDERS-SERVICE/orders/getOrdersByPickUpDate/"+date,PickedUpOrders[].class);
		Sales sales=new Sales();
		sales.setDateAndTime(date);
		List<String> drugName=new ArrayList<>();
		double total=0;
		for(PickedUpOrders pobj:order) {
			drugName.add(pobj.getDrugName());
			total=total+pobj.getTotal();
		}
		sales.setBalance(0);
		sales.setPaidAmt(total);
		sales.setDrugName(drugName);
		sales.setTotalAmt(total);
		Random random = new Random();
        int randomNumber = random.nextInt(Integer.MAX_VALUE);
        sales.setSalesId(randomNumber);
		return salesRepo.save(sales);
	}

	public List<Sales> getReports() {
		return salesRepo.findAll();
	}

	public boolean deleteSalesReport(int id) {
		salesRepo.deleteById(id);
		Sales sales=salesRepo.findById(id).orElse(null);
		if(sales==null) {
			return true;
		}
		return false;
	}
}
