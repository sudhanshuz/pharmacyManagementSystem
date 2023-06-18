package com.pms.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.users.model.User;
@Repository
public interface UserRepository extends MongoRepository<User, Long> {

}
