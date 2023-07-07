package com.pms.users.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.users.model.Drugs;
import com.pms.users.model.Orders;
import com.pms.users.model.Supplier;
import com.pms.users.repository.OrdersRepo;


@Service
public class MasterService {
	
	Logger logger=LoggerFactory.getLogger(MasterService.class);
	

	@Autowired
	OrdersRepo ordersRepo;

	
	@Autowired
	private RestTemplate restTemplate;

	public Supplier addSupplier(Supplier supplier) {
		String url="http://SUPPLIER-SERVICE/supplier/add";
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Supplier> requestEntity = new HttpEntity<>(supplier, headers);
       
        Supplier addedSupplier=restTemplate.postForObject(url, requestEntity,Supplier.class);
		
		
		return addedSupplier;
	}

	public List<Orders> addAllOrders() {
		String url="http://ORDERS-SERVICE/orders/viewPickedUpOrders";
		ResponseEntity<Orders[]> orders=restTemplate.getForEntity(url,Orders[].class);
		Orders[] ordersList=orders.getBody();
		for(Orders order:ordersList) {
			ordersRepo.insert(order);
		}
		return ordersRepo.findAll();
	}

	public String editSupplier(Supplier supplier) {
		restTemplate.put("http://SUPPLIER-SERVICE/supplier/edit", supplier);
		return "supplier updated successfully";
	}

	public String deleteSupplier(int supplierId) {
			restTemplate.delete("http://SUPPLIER-SERVICE/supplier/delete/"+supplierId, supplierId);
		return "supplier deleted succesfully";
	}

	public Supplier[] viewAllSuppliers() {
		String url="http://SUPPLIER-SERVICE/supplier/getAll";
		ResponseEntity<Supplier[]> supplier=restTemplate.getForEntity(url,Supplier[].class);
		Supplier[] supplierList=supplier.getBody();
		return supplierList;
	}

	public Orders[] viewPickedUpOrders() {
		String url="http://ORDERS-SERVICE/orders/viewPickedUpOrders";
		ResponseEntity<Orders[]> orders=restTemplate.getForEntity(url,Orders[].class);
		Orders[] ordersList=orders.getBody();
		return ordersList;
		
	}

	public Drugs addDrugs(Drugs drug) {
		String url="http://SUPPLIER-SERVICE/drugs/add";
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Drugs> requestEntity = new HttpEntity<>(drug, headers);
       
        Drugs addedDrug=restTemplate.postForObject(url, requestEntity,Drugs.class);
		return addedDrug;
	}

	public String editDrugs(Drugs drug) {
		restTemplate.put("http://SUPPLIER-SERVICE/drugs/edit", drug);
		return "drug details updated successfully";
	}

	public String deleteDrugs(String drugName) {
		restTemplate.delete("http://SUPPLIER-SERVICE/drugs/deleteDrugByName/"+drugName,drugName);
		return "drug deleted successfully";
	}

	public Drugs[] viewDrugs() {
		String url="http://SUPPLIER-SERVICE/drugs/getAll";
		ResponseEntity<Drugs[]> drugs=restTemplate.getForEntity(url,Drugs[].class);
		Drugs[] drugsList=drugs.getBody();
		return drugsList;
	}

	public Orders placeOrder(Orders order) {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Orders> requestEntity = new HttpEntity<>(order, headers);
        Orders orderUpdated=restTemplate.postForObject("http://ORDERS-SERVICE/orders/add", requestEntity, Orders.class);
        //send msg to admin regarding new order
		return orderUpdated;
	}

	public Orders verifyOrders(long orderId) {
		Orders order=restTemplate.getForObject("http://ORDERS-SERVICE/orders/getOrdersById/"+orderId,Orders.class);
		order.setVerified(true);
		System.out.println(order);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Orders> requestEntity = new HttpEntity<>(order, headers);
        Orders orderUpdated=restTemplate.postForObject("http://ORDERS-SERVICE/orders/addVerifiedOrders",requestEntity, Orders.class);
		
		return  orderUpdated;
	}


}
