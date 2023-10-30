package com.example.servicemanagement.services;

import com.example.servicemanagement.dtos.RequestSProviderDto;
import com.example.servicemanagement.dtos.ResponseSProviderDto;
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

    public ResponseSProviderDto createServiceProvider(RequestSProviderDto requestDto) {
        // todo: if the SP is already present

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName(requestDto.getName());
        serviceProvider.setAddress(requestDto.getAddress());
        serviceProvider.setPhoneNo(requestDto.getPhoneNo());
        serviceProvider.setCategory(requestDto.getCategory());

        ServiceProvider savedSavedProvider = sProviderRepository.save(serviceProvider);
        return ResponseSProviderDto.from(savedSavedProvider);
    }

    public ResponseSProviderDto getServiceProvider(String id) throws Exception {
        Optional<ServiceProvider> serviceProvider = sProviderRepository.findById(id);
        if(serviceProvider.isEmpty())
            throw new Exception("Service Provider is not present");
        return ResponseSProviderDto.from(serviceProvider.get());
    }

    public List<ResponseSProviderDto> getAllServiceProviders() {
        List<ServiceProvider> serviceProviders = sProviderRepository.findAll();
        return serviceProviders.stream()
                .map(ResponseSProviderDto::from)
                .toList();
    }

    public ResponseSProviderDto updateServiceProvider(RequestSProviderDto updateRequestDto, String id) throws Exception {
        Optional<ServiceProvider> optionalServiceProvider = sProviderRepository.findById(id);
        if(optionalServiceProvider.isEmpty())
            throw new Exception("Service Provider is not present");

        ServiceProvider existingServiceProvider = optionalServiceProvider.get();
        existingServiceProvider.setName(updateRequestDto.getName());
        existingServiceProvider.setAddress(updateRequestDto.getAddress());
        existingServiceProvider.setPhoneNo(updateRequestDto.getPhoneNo());
        existingServiceProvider.setCategory(updateRequestDto.getCategory());

        ServiceProvider updatedServiceProvider = sProviderRepository.save(existingServiceProvider);
        return ResponseSProviderDto.from(updatedServiceProvider);
    }

    public ResponseSProviderDto deleteServiceProvider(String id) {
        sProviderRepository.deleteById(id);
        return ResponseSProviderDto.empty();
    }
}
