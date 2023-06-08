package com.pms.supplier.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pms.supplier.model.Drugs;

public interface DrugRepository extends MongoRepository<Drugs,String> {

}
