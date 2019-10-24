package com.workshop.mongodb.workshopMongodb.repository;

import com.workshop.mongodb.workshopMongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
