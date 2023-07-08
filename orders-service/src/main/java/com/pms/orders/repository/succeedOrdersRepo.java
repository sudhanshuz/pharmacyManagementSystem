package com.pms.orders.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pms.orders.model.succeedOrders;

public interface succeedOrdersRepo extends MongoRepository<succeedOrders,Long> {

	@Query("{ 'userId' : ?0 }")
    List<succeedOrders> findByUserId(String userId);
}
