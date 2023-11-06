package com.example.servicemanagement.repositories;

import com.example.servicemanagement.models.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpRepository extends MongoRepository<ServiceProvider, String> {
    Optional<ServiceProvider> findByEmail(String email);

    @Override
    <S extends ServiceProvider> S save(S serviceProvider);

    @Override
    List<ServiceProvider> findAll();

    @Override
    Optional<ServiceProvider> findById(String s);

    @Override
    void delete(ServiceProvider entity);
}
