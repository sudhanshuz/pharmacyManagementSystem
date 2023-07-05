package com.pms.supplier.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pms.supplier.model.Supplier;

public interface SupplierRepository extends MongoRepository<Supplier, Integer> {
	@Query("{ 'supplierName' : ?0 }")
	Supplier findBySupplierName(String supplierName);
}
