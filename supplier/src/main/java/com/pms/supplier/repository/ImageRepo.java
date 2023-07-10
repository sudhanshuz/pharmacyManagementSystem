package com.pms.supplier.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pms.supplier.model.ImageModel;

public interface ImageRepo extends MongoRepository<ImageModel, Long> {
	@Query("{ 'name' : ?0 }")
	Optional<ImageModel> findByName(String name);
}
