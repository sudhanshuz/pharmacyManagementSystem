package com.pms.supplier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.supplier.model.Drugs;
import com.pms.supplier.model.Supplier;
import com.pms.supplier.repository.DrugRepository;
import com.pms.supplier.repository.SupplierRepository;



@Service
public class service {
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
}
