package com.pms.users.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.pms.users.model.User;
@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    @Query("{ 'name' : ?0 }")
    Optional<User> findByName(String name);
}
