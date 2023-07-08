package com.pms.orders.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pms.orders.model.Orders;
import com.pms.orders.model.PickedUpOrders;

@Repository
public interface PickedUpOrdersRepo extends MongoRepository<PickedUpOrders,Long> {

	@Query("{ 'docName' : ?0 }")
    List<PickedUpOrders> findByDocName(String docName);
	
	@Query("{ 'pickupDate' : ?0 }")
    List<PickedUpOrders> findByPickUpDate(String pickupDate);
}
