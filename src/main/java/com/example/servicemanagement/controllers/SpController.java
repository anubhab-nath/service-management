package com.example.servicemanagement.controllers;

import com.example.servicemanagement.dtos.RequestSpDto;
import com.example.servicemanagement.dtos.ResponseSpDto;
import com.example.servicemanagement.exceptions.NotFoundException;
import com.example.servicemanagement.exceptions.ValueNotAllowedException;
import com.example.servicemanagement.services.SpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service-provider")
public class SpController {
    private final SpService spService;

    @Autowired
    public SpController(SpService spService) {
        this.spService = spService;
    }

    @PostMapping
    public ResponseEntity<ResponseSpDto> addServiceProvider(
            @RequestBody RequestSpDto createRequestDto
    ) throws ValueNotAllowedException {
        ResponseSpDto responseSpDto = spService.createServiceProvider(createRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseSpDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSpDto> retrieveServiceProvider(@PathVariable("id") String id) throws NotFoundException {
        ResponseSpDto responseSpDto = spService.getServiceProviderById(id);
        return ResponseEntity.ok(responseSpDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseSpDto>> retrieveAllServiceProviders() {
        List<ResponseSpDto> responseSpDtos = spService.getAllServiceProviders();
        return ResponseEntity.ok(responseSpDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSpDto> updateServiceProvider(
            @RequestBody RequestSpDto updateRequestDto,
            @PathVariable("id") String id
    ) throws NotFoundException {
        ResponseSpDto responseSpDto = spService.updateServiceProviderById(updateRequestDto, id);
        return ResponseEntity.ok(responseSpDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSpDto> deleteServiceProvider(@PathVariable("id") String id) throws NotFoundException {
        ResponseSpDto responseSpDto = spService.deleteServiceProviderById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(responseSpDto);
    }
}
