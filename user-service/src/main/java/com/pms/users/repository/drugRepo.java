package com.pms.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pms.users.model.Drugs;

public interface drugRepo extends MongoRepository<Drugs,String> {

}
