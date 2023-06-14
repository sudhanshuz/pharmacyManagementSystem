package com.pms.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pms.orders.model.Orders;

public interface OrdersRepository extends MongoRepository<Orders, Long> {

}
