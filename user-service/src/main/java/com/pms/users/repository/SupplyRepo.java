package com.pms.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.users.model.Supplier;

@Repository
public interface SupplyRepo extends MongoRepository<Supplier, Integer>{
public Supplier findBySupplierPhoneNo(long phoneNo);
}
