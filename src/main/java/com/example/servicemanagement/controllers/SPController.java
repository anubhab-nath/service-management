package com.example.servicemanagement.controllers;

import com.example.servicemanagement.dtos.RequestSPDto;
import com.example.servicemanagement.dtos.ResponseSPDto;
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
@RequestMapping("/service-provider")
public class SPController {
    private final SProviderService sProviderService;

    @Autowired
    public SPController(SProviderService sProviderService) {
        this.sProviderService = sProviderService;
    }

    @PostMapping
    public ResponseEntity<ResponseSPDto> addServiceProvider(
            @RequestBody RequestSPDto createRequestDto
    ) {
        ResponseSPDto sProviderDto = sProviderService.createServiceProvider(createRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sProviderDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSPDto> retrieveServiceProvider(@PathVariable("id") String id) throws Exception {
        ResponseSPDto sProviderDto = sProviderService.getServiceProvider(id);
        return ResponseEntity.ok(sProviderDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseSPDto>> retrieveAllServiceProviders() {
        List<ResponseSPDto> sProviderDtos = sProviderService.getAllServiceProviders();
        return ResponseEntity.ok(sProviderDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSPDto> updateServiceProvider(
            @RequestBody RequestSPDto updateRequestDto,
            @PathVariable("id") String id
    ) throws Exception {
        ResponseSPDto sProviderDto = sProviderService.updateServiceProvider(updateRequestDto, id);
        return ResponseEntity.ok(sProviderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSPDto> deleteServiceProvider(@PathVariable("id") String id) {
        ResponseSPDto sProviderDto = sProviderService.deleteServiceProvider(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(sProviderDto);
    }
}
