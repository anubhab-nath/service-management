package com.example.servicemanagement.repositories;

import com.example.servicemanagement.models.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpRepository extends MongoRepository<ServiceProvider, String> {
    Optional<ServiceProvider> findByName(String name);
}
