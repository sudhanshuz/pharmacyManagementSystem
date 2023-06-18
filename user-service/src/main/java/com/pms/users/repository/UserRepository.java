package com.pms.users.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.pms.users.model.User;
@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    @Query("{ 'name' : ?0 }")
    User findByName(String name);
}