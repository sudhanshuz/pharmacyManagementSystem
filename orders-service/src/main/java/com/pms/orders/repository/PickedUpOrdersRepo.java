package com.pms.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.orders.model.Orders;
import com.pms.orders.model.PickedUpOrders;

@Repository
public interface PickedUpOrdersRepo extends MongoRepository<Orders,Long> {

}
