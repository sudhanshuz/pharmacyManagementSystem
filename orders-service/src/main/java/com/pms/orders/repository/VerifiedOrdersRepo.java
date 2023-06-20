package com.pms.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pms.orders.model.VerifiedOrders;

public interface VerifiedOrdersRepo extends MongoRepository<VerifiedOrders,Long> {

}
