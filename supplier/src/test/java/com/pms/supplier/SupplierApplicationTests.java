package com.pms.supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Drugs;
import com.pms.supplier.model.Supplier;
import com.pms.supplier.repository.DrugRepository;
import com.pms.supplier.repository.SupplierRepository;
import com.pms.supplier.service.SupplyService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class SupplierApplicationTests {
@Autowired
private SupplyService supplyService;

@MockBean
private SupplierRepository supplierRepository;
@MockBean
private DrugRepository drugRepository;

	
	@Test
	 void viewSuppliers() {
		when(supplierRepository.findAll()).thenReturn(Stream.of
				(new Supplier(101,"suresh","sb@gmail.com",878849403,null),
						new Supplier(102,"ramesh","rb@gmail.com",878899403,null)).collect(Collectors.toList()));
		assertEquals(2,supplyService.viewSuppliers().size());
	}
	@Test
	 void insertSuppliers() throws ResourceNotFoundException {
		Supplier supplier=new Supplier(101,"suresh","sb@gmail.com",878849403,null);
		when(supplierRepository.insert(supplier)).thenReturn(supplier);
		assertEquals(supplier,supplyService.insertSuppliers(supplier));
	}
//	@Test
//	public void addStock() throws ResourceNotFoundException, ParseException {
//		Supplier supplier=new Supplier(101,"suresh","sb@gmail.com",878849403,null);
//		when(supplierRepository.save(supplier)).thenReturn(supplier);
//		Drugs drug=new Drugs("crocin500",2,new SimpleDateFormat("dd/MM/yyyy").parse("30/11/2025"),"lab01"); 
//		when(drugRepository.findById("crocin500").orElse(null)).thenReturn(drug);
//		assertEquals(supplier,supplyService.addStock(supplier.getSupplierId(),drug.getDrugName(),500));
//	}

}
