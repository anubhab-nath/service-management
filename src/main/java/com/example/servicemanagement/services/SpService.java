package com.example.servicemanagement.services;

import com.example.servicemanagement.dtos.RequestSpDto;
import com.example.servicemanagement.dtos.ResponseSpDto;
import com.example.servicemanagement.exceptions.NotFoundException;
import com.example.servicemanagement.exceptions.ValueNotAllowedException;
import com.example.servicemanagement.models.ServiceProvider;
import com.example.servicemanagement.repositories.SpRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpService {
    private final SpRepository spRepository;

    public SpService(SpRepository spRepository) {
        this.spRepository = spRepository;
    }

    public ResponseSpDto createServiceProvider(RequestSpDto createRequestDto) throws Exception {
        Optional<ServiceProvider> existingServiceProvider = spRepository.findByName(createRequestDto.getName());
        if(existingServiceProvider.isPresent())
            throw new ValueNotAllowedException("VNA_001", "Service Provider with provided name is already present");

        ServiceProvider newServiceProvider = ServiceProvider.from(createRequestDto);
        ServiceProvider savedSavedProvider = spRepository.save(newServiceProvider);
        return ResponseSpDto.from(savedSavedProvider);
    }

    public ResponseSpDto getServiceProvider(String id) throws Exception {
        Optional<ServiceProvider> serviceProvider = spRepository.findById(id);
        if(serviceProvider.isEmpty())
            throw new NotFoundException("NF_001", "Service Provider");
        return ResponseSpDto.from(serviceProvider.get());
    }

    public List<ResponseSpDto> getAllServiceProviders() {
        List<ServiceProvider> serviceProviders = spRepository.findAll();
        return serviceProviders.stream()
                .map(ResponseSpDto::from)
                .toList();
    }

    public ResponseSpDto updateServiceProvider(RequestSpDto updateRequestDto, String id) throws Exception {
        Optional<ServiceProvider> optionalServiceProvider = spRepository.findById(id);
        if(optionalServiceProvider.isEmpty())
            throw new NotFoundException("NF_002", "Service Provider");


        ServiceProvider existingServiceProvider = optionalServiceProvider.get().updateFrom(updateRequestDto);

        ServiceProvider updatedServiceProvider = spRepository.save(existingServiceProvider);
        return ResponseSpDto.from(updatedServiceProvider);
    }

    public ResponseSpDto deleteServiceProvider(String id) throws NotFoundException {
        Optional<ServiceProvider> optionalServiceProvider = spRepository.findById(id);
        if(optionalServiceProvider.isEmpty())
            throw new NotFoundException("NF_003", "Service Provider");

        spRepository.deleteById(id);
        return ResponseSpDto.empty();
    }
}
