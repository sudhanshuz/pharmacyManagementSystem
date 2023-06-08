package com.pms.supplier.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Supplier insertSuppliers(Supplier supplier) {
		supplierRepository.insert(supplier);
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

	public Supplier addStock(int id,String drugName,int qty) {
		// TODO Auto-generated method stub
		Supplier supplier=supplierRepository.findById(id).orElse(null);
		//if supplier is null then invalid Id
		Drugs drug=drugRepository.findById(drugName).orElse(null);
		//if drug is null then invalid drug name or drug is not available
		List<HashMap<String,Integer>> stocks=new ArrayList<HashMap<String,Integer>>();
		stocks=supplier.getStock();
		HashMap<String,Integer> stock1=new HashMap<String,Integer>();
		stock1.put(drug.getDrugName(),qty);
		stocks.add(stock1);
		supplier.setStock(stocks);
		return supplierRepository.save(supplier);
	}

	public Drugs viewDrugByName(String name) {
		// TODO Auto-generated method stub
		return drugRepository.findById(name).orElse(null);
	}
}
