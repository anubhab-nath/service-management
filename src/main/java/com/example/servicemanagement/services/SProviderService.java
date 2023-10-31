package com.example.servicemanagement.services;

import com.example.servicemanagement.dtos.RequestSPDto;
import com.example.servicemanagement.dtos.ResponseSPDto;
import com.example.servicemanagement.models.ServiceProvider;
import com.example.servicemanagement.repositories.SProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SProviderService {
    private final SProviderRepository sProviderRepository;

    public SProviderService(SProviderRepository sProviderRepository) {
        this.sProviderRepository = sProviderRepository;
    }

    public ResponseSPDto createServiceProvider(RequestSPDto requestDto) {
        // todo: if the SP is already present

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName(requestDto.getName());
        serviceProvider.setAddress(requestDto.getAddress());
        serviceProvider.setPhoneNo(requestDto.getPhoneNo());
        serviceProvider.setCategory(requestDto.getCategory());

        ServiceProvider savedSavedProvider = sProviderRepository.save(serviceProvider);
        return ResponseSPDto.from(savedSavedProvider);
    }

    public ResponseSPDto getServiceProvider(String id) throws Exception {
        Optional<ServiceProvider> serviceProvider = sProviderRepository.findById(id);
        if(serviceProvider.isEmpty())
            throw new Exception("Service Provider is not present");
        return ResponseSPDto.from(serviceProvider.get());
    }

    public List<ResponseSPDto> getAllServiceProviders() {
        List<ServiceProvider> serviceProviders = sProviderRepository.findAll();
        return serviceProviders.stream()
                .map(ResponseSPDto::from)
                .toList();
    }

    public ResponseSPDto updateServiceProvider(RequestSPDto updateRequestDto, String id) throws Exception {
        Optional<ServiceProvider> optionalServiceProvider = sProviderRepository.findById(id);
        if(optionalServiceProvider.isEmpty())
            throw new Exception("Service Provider is not present");

        ServiceProvider existingServiceProvider = optionalServiceProvider.get();
        existingServiceProvider.setName(updateRequestDto.getName());
        existingServiceProvider.setAddress(updateRequestDto.getAddress());
        existingServiceProvider.setPhoneNo(updateRequestDto.getPhoneNo());
        existingServiceProvider.setCategory(updateRequestDto.getCategory());

        ServiceProvider updatedServiceProvider = sProviderRepository.save(existingServiceProvider);
        return ResponseSPDto.from(updatedServiceProvider);
    }

    public ResponseSPDto deleteServiceProvider(String id) {
        sProviderRepository.deleteById(id);
        return ResponseSPDto.empty();
    }
}
