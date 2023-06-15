package com.pms.orders.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pms.orders.model.Orders;

public interface OrdersRepository extends MongoRepository<Orders, Long> {

	@Query("{ 'docName' : ?0 }")
    List<Orders> findByDocName(String docName);
}
