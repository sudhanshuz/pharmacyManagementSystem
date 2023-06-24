package com.pms.supplier.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Drugs;
import com.pms.supplier.model.Orders;
import com.pms.supplier.model.Supplier;
import com.pms.supplier.repository.DrugRepository;
import com.pms.supplier.repository.SupplierRepository;



@Service
public class SupplyService {
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private DrugRepository drugRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Supplier> viewSuppliers() {
		return supplierRepository.findAll();
	}
	
	public Supplier insertSuppliers(Supplier supplier){
		supplierRepository.insert(supplier);
		return supplier;
	}

	public  List<Drugs> getDrugs() {
		return drugRepository.findAll();
	}

	public Drugs insertDrug(Drugs drug) {
		return drugRepository.insert(drug);
	}

	public Supplier addStock(int id,String drugName,int qty) throws ResourceNotFoundException {
		Supplier supplier=supplierRepository.findById(id).orElse(null);
		//if supplier is null then invalid Id
		if(supplier==(null)) {
			throw new ResourceNotFoundException("Invalid Id");
		}
		Drugs drug=drugRepository.findById(drugName).orElse(null);
		//if drug is null then invalid drug name or drug is not available
		if(drug==(null)) {
			throw new ResourceNotFoundException("Invalid drug name");
		}
		List<HashMap<String,Integer>> stocks;
		stocks=supplier.getStock();
		HashMap<String,Integer> stock1=new HashMap<>();
		stock1.put(drug.getDrugName(),qty);
		stocks.add(stock1);
		supplier.setStock(stocks);
		return supplierRepository.save(supplier);
	}

	public Drugs viewDrugByName(String name) throws ResourceNotFoundException {
		return drugRepository.findById(name).orElseThrow(()->new ResourceNotFoundException("drug not found"));
	}

	public double getDrugPrice(String drugName) throws ResourceNotFoundException {
		Drugs drug=drugRepository.findById(drugName).orElse(null);
		if(drug==(null)) {
			throw new ResourceNotFoundException(drugName+ "not available currently");
		}
		return drug.getPrice();
	}

	public Drugs deleteDrugs(String drugName) throws ResourceNotFoundException {
		Drugs drug=drugRepository.findById(drugName).orElse(null);
		if(drug==null) {
			throw new ResourceNotFoundException(drugName+ "not available currently");
		}
		drugRepository.delete(drug);
		return drug;
	}

	public Drugs editDrugs(Drugs drug1) {
		return drugRepository.save(drug1);
	}

	public Supplier deleteSupplier(int id) throws ResourceNotFoundException {
		
		supplierRepository.deleteById(id);
		return supplierRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("supplier not found"));
	}

	public Supplier editSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	
	public String pickUpOrder(Long orderId,int supplierId) {
		Orders[] orders= restTemplate.getForObject("http://ORDERS-SERVICE/orders/viewVerifiedOrders",Orders[].class);
		for(Orders order:orders) {
			if(order.getOrderId()==orderId) {
				order.setPickedUp(true);
				order.setPickupDate(new Date());
				order.setSupplierId(supplierId);
				HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);

		        HttpEntity<Orders> requestEntity = new HttpEntity<>(order, headers);
				restTemplate.postForObject("http://ORDERS-SERVICE/orders/addPickedUpOrders", requestEntity, Orders.class);
				break;
			}
		}
		return "order has been picked up successfully";
	}

	public Orders[] serviceObj() {
		return restTemplate.getForObject("http://ORDERS-SERVICE/orders/viewVerifiedOrders",Orders[].class);
	}

	public List<Orders> findMyOrders(int supplierId) {
		Orders[] orders= restTemplate.getForObject("http://ORDERS-SERVICE/orders/viewPickedUpOrders",Orders[].class);
		List<Orders> myOrders=new ArrayList<>();
		for(Orders order:orders) {
			if(order.getSupplierId()==supplierId) {
			myOrders.add(order);
			}
		}
		return myOrders;
	}
}
