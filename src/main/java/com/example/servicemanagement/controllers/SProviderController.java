package com.example.servicemanagement.controllers;

import com.example.servicemanagement.dtos.RequestSProviderDto;
import com.example.servicemanagement.dtos.ResponseSProviderDto;
import com.example.servicemanagement.services.SProviderService;
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
@RequestMapping("/sp")
public class SProviderController {
    private final SProviderService sProviderService;

    @Autowired
    public SProviderController(SProviderService sProviderService) {
        this.sProviderService = sProviderService;
    }

    @PostMapping
    public ResponseEntity<ResponseSProviderDto> addServiceProvider(
            @RequestBody RequestSProviderDto createRequestDto
    ) {
        ResponseSProviderDto sProviderDto = sProviderService.createServiceProvider(createRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sProviderDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSProviderDto> retrieveServiceProvider(@PathVariable("id") String id) throws Exception {
        ResponseSProviderDto sProviderDto = sProviderService.getServiceProvider(id);
        return ResponseEntity.ok(sProviderDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseSProviderDto>> retrieveAllServiceProviders() {
        List<ResponseSProviderDto> sProviderDtos = sProviderService.getAllServiceProviders();
        return ResponseEntity.ok(sProviderDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSProviderDto> updateServiceProvider(
            @RequestBody RequestSProviderDto updateRequestDto,
            @PathVariable("id") String id
    ) throws Exception {
        ResponseSProviderDto sProviderDto = sProviderService.updateServiceProvider(updateRequestDto, id);
        return ResponseEntity.ok(sProviderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSProviderDto> deleteServiceProvider(@PathVariable("id") String id) {
        ResponseSProviderDto sProviderDto = sProviderService.deleteServiceProvider(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(sProviderDto);
    }
}
