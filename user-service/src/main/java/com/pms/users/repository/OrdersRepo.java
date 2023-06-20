package com.pms.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pms.users.model.Orders;


public interface OrdersRepo extends MongoRepository<Orders,Long> {

}
