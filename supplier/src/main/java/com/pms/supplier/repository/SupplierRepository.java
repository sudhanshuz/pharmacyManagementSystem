package com.pms.supplier.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pms.supplier.model.Supplier;

public interface SupplierRepository extends MongoRepository<Supplier, Integer> {

}
