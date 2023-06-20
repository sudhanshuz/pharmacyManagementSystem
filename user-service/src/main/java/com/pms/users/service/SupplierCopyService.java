package com.pms.users.service;

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
import com.pms.users.repository.SupplyRepo;
import com.pms.users.repository.drugRepo;

@Service
public class SupplierCopyService {
	
	Logger logger=LoggerFactory.getLogger(SupplierCopyService.class);
	
	@Autowired
	private SupplyRepo supplyRepo;
	@Autowired
	OrdersRepo ordersRepo;
	@Autowired
	drugRepo drugrepo;
	
	@Autowired
	private RestTemplate restTemplate;

	public List<Supplier> addSupplier() {
		String url="http://SUPPLIER-SERVICE/supplier/getAll";
		
		ResponseEntity<Supplier[]> suppliers=restTemplate.getForEntity(url,Supplier[].class);
		
		Supplier[] supp=suppliers.getBody();
		//implement logic to verify supplier and then add them
		for(Supplier sObj:supp) {
			if(supplyRepo.findBySupplierPhoneNo(sObj.getSupplierPhoneNo())!=null){
				//send message as contact already exists
				logger.info(sObj.getSupplierPhoneNo()+"contact already exists");
			}
			supplyRepo.insert(sObj);
		}
		return supplyRepo.findAll();
	}

	public List<Orders> addAllOrders() {
		// TODO Auto-generated method stub
		String url="http://ORDERS-SERVICE/orders/getAll";
		ResponseEntity<Orders[]> orders=restTemplate.getForEntity(url,Orders[].class);
		Orders[] ordersList=orders.getBody();
		for(Orders order:ordersList) {
			ordersRepo.insert(order);
		}
		return ordersRepo.findAll();
	}

	public Supplier editSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		int supId=supplier.getSupplierId();
		if(supplyRepo.findById(supId)==null) {
			logger.info("invalid supplier");
		}
		return supplyRepo.save(supplier);
	}

	public String deleteSupplier(int supplierId) {
		// TODO Auto-generated method stub
		try {
		supplyRepo.deleteById(supplierId);}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "supplier deleted succesfully";
	}

	public List<Supplier> viewAllSuppliers() {
		// TODO Auto-generated method stub
		return supplyRepo.findAll();
	}

	public List<Orders> viewAllOrders() {
		// TODO Auto-generated method stub
		return ordersRepo.findAll();
		
	}

	public List<Drugs> addDrugs() {
		// TODO Auto-generated method stub
		String url="http://SUPPLIER-SERVICE/drugs/getAll";
		ResponseEntity<Drugs[]> drugs=restTemplate.getForEntity(url,Drugs[].class);
		Drugs[] drugsList=drugs.getBody();
		for(Drugs drugObj:drugsList) {
			drugrepo.insert(drugObj);
		}
		return drugrepo.findAll();
	}

	public Drugs editDrugs(Drugs drug) {
		// TODO Auto-generated method stub
		return drugrepo.save(drug);
	}

	public String deleteDrugs(String drugName) {
		// TODO Auto-generated method stub
		drugrepo.deleteById(drugName);
		return "drug deleted successfully";
	}

	public List<Drugs> viewDrugs() {
		// TODO Auto-generated method stub
		return drugrepo.findAll();
	}

	public Orders placeOrder(Orders order) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Orders> requestEntity = new HttpEntity<>(order, headers);
        Orders orderUpdated=restTemplate.postForObject("http://ORDERS-SERVICE/orders/add", requestEntity, Orders.class);
        //=restTemplate.getForObject("http://ORDERS-SERVICE/orders/getOrdersById"+,Orders.class);
		return ordersRepo.save(orderUpdated);
	}

	public List<Orders> verifyOrders() {
		// TODO Auto-generated method stub
		List<Orders> ordersList=ordersRepo.findAll();
		List<Orders> verifiedList=null;
		for(Orders order:ordersList) {
			if(!order.isVerified()) {
				//check if order is valid or not
				order.setVerified(true);
				verifiedList.add(order);
			}
		}
		return  verifiedList;
	}

}
