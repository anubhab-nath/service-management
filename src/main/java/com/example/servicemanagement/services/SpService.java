package com.example.servicemanagement.services;

import com.example.servicemanagement.dtos.RequestSpDto;
import com.example.servicemanagement.dtos.ResponseSpDto;
import com.example.servicemanagement.exceptions.MissingRequestBodyException;
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

    public ResponseSpDto createServiceProvider(RequestSpDto createRequestDto) throws ValueNotAllowedException, MissingRequestBodyException {
        if(createRequestDto == null)
            throw new MissingRequestBodyException("RequestDto is null");

        Optional<ServiceProvider> existingServiceProvider = spRepository.findByEmail(createRequestDto.getEmail());
        if(existingServiceProvider.isPresent())
            throw new ValueNotAllowedException("VNA_001", "Email is already used by another Service Provider");

        ServiceProvider newServiceProvider = ServiceProvider.from(createRequestDto);
        ServiceProvider savedServiceProvider = spRepository.save(newServiceProvider);
        return ResponseSpDto.from(savedServiceProvider);
    }

    public ResponseSpDto getServiceProviderById(String spId) throws NotFoundException {
        Optional<ServiceProvider> serviceProvider = spRepository.findById(spId);
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

    public ResponseSpDto updateServiceProviderById(RequestSpDto updateRequestDto, String spId) throws NotFoundException {
        Optional<ServiceProvider> optionalServiceProvider = spRepository.findById(spId);
        if(optionalServiceProvider.isEmpty())
            throw new NotFoundException("NF_002", "Service Provider");

        ServiceProvider updatedServiceProvider = optionalServiceProvider.get().updateFrom(updateRequestDto);
        ServiceProvider savedServiceProvider = spRepository.save(updatedServiceProvider);
        return ResponseSpDto.from(savedServiceProvider);
    }

    public ResponseSpDto deleteServiceProviderById(String spId) throws NotFoundException {
        Optional<ServiceProvider> optionalServiceProvider = spRepository.findById(spId);
        if(optionalServiceProvider.isEmpty())
            throw new NotFoundException("NF_003", "Service Provider");

        spRepository.delete(optionalServiceProvider.get());
        return ResponseSpDto.empty();
    }
}
