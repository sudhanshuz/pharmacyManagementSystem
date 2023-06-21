package com.pms.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.orders.model.NewOrders;

@Repository
public interface NewOrdersRepo extends MongoRepository<NewOrders,Long> {

}
