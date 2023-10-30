package com.example.servicemanagement.repositories;

import com.example.servicemanagement.models.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SProviderRepository extends MongoRepository<ServiceProvider, String> {
}
