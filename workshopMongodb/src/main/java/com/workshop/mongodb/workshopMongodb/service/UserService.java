package com.workshop.mongodb.workshopMongodb.service;

import com.workshop.mongodb.workshopMongodb.domain.User;
import com.workshop.mongodb.workshopMongodb.repository.UserRepository;
import com.workshop.mongodb.workshopMongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuario not Found"));
    }
}
