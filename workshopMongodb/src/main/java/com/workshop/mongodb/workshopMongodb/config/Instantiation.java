package com.workshop.mongodb.workshopMongodb.config;

import com.workshop.mongodb.workshopMongodb.domain.User;
import com.workshop.mongodb.workshopMongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        User user = new User(null, "jean", "jeanfp");
        User user1 = new User(null, "teste", "jeanfp");
        User user2 = new User(null, "teste2", "jeanfp");

        repository.saveAll(Arrays.asList(user, user1, user2));
    }
}
