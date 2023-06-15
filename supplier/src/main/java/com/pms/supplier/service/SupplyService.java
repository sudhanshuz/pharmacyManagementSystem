package com.pms.supplier.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Drugs;
import com.pms.supplier.model.Supplier;
import com.pms.supplier.repository.DrugRepository;
import com.pms.supplier.repository.SupplierRepository;



@Service
public class SupplyService {
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private DrugRepository drugRepository;
	
	public List<Supplier> viewSuppliers() {
		return supplierRepository.findAll();
	}
	
	public Supplier insertSuppliers(Supplier supplier) throws ResourceNotFoundException {
		supplierRepository.insert(supplier);
		if(supplier.equals(null)) {
			throw new ResourceNotFoundException("please specify all info");
		}
		return supplier;
	}

	public  List<Drugs> getDrugs() {
		// TODO Auto-generated method stub
		return drugRepository.findAll();
	}

	public Drugs insertDrug(Drugs drug) {
		// TODO Auto-generated method stub
		return drugRepository.insert(drug);
	}

	public Supplier addStock(int id,String drugName,int qty) throws ResourceNotFoundException {
		Supplier supplier=supplierRepository.findById(id).orElse(null);
		//if supplier is null then invalid Id
		if(supplier.equals(null)) {
			throw new ResourceNotFoundException("Invalid Id");
		}
		Drugs drug=drugRepository.findById(drugName).orElse(null);
		//if drug is null then invalid drug name or drug is not available
		if(drug.equals(null)) {
			throw new ResourceNotFoundException("Invalid drug name");
		}
		List<HashMap<String,Integer>> stocks=new ArrayList<>();
		stocks=supplier.getStock();
		HashMap<String,Integer> stock1=new HashMap<>();
		stock1.put(drug.getDrugName(),qty);
		stocks.add(stock1);
		supplier.setStock(stocks);
		return supplierRepository.save(supplier);
	}

	public Drugs viewDrugByName(String name) {
		return drugRepository.findById(name).orElse(null);
	}

	public double getDrugPrice(String drugName) throws ResourceNotFoundException {
		Drugs drug=drugRepository.findById(drugName).orElse(null);
		if(drug.equals(null)) {
			throw new ResourceNotFoundException(drugName+ "not available currently");
		}
		double price=drug.getPrice();
		return price;
	}
}
