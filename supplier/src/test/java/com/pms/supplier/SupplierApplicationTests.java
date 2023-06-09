package com.pms.supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
import com.pms.supplier.model.Supplier;
import com.pms.supplier.repository.SupplierRepository;
import com.pms.supplier.service.SupplyService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class SupplierApplicationTests {
@Autowired
private SupplyService supplyService;

@MockBean
private SupplierRepository supplierRepository;
	@Test
	void contextLoads() {
	}
	
	@Test
	public void viewSuppliers() {
		when(supplierRepository.findAll()).thenReturn(Stream.of
				(new Supplier(101,"suresh","sb@gmail.com",878849403,null),
						new Supplier(102,"ramesh","rb@gmail.com",878899403,null)).collect(Collectors.toList()));
		assertEquals(2,supplyService.viewSuppliers().size());
	}
	
	public void insertSuppliers(Supplier supplier) throws ResourceNotFoundException {
		supplier=new Supplier(101,"suresh","sb@gmail.com",878849403,null);
		when(supplierRepository.insert(supplier)).thenReturn(supplier);
		assertEquals(supplier,supplyService.insertSuppliers(supplier));
	}

}
