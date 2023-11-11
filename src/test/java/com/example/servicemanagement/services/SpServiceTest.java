package com.example.servicemanagement.services;

import com.example.servicemanagement.dtos.RequestSpDto;
import com.example.servicemanagement.dtos.ResponseSpDto;
import com.example.servicemanagement.exceptions.MissingRequestBodyException;
import com.example.servicemanagement.exceptions.NotFoundException;
import com.example.servicemanagement.exceptions.ValueNotAllowedException;
import com.example.servicemanagement.models.Category;
import com.example.servicemanagement.models.ServiceProvider;
import com.example.servicemanagement.repositories.SpRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ContextConfiguration
public class SpServiceTest {

    @InjectMocks
    private SpService spService;
    @Mock
    private SpRepository spRepository;

    private AutoCloseable autoCloseable;
    private RequestSpDto requestSpDto;
    private ServiceProvider serviceProvider;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        String category = "DINE_IN";
        requestSpDto = RequestSpDto.builder()
                .email("email")
                .name("name")
                .phoneNo("phoneNo")
                .address("address")
                .category("DINE_IN")
                .build();

        serviceProvider = new ServiceProvider();
        serviceProvider.setId("spid");
        serviceProvider.setName("name");
        serviceProvider.setEmail("email");
        serviceProvider.setAddress("address");
        serviceProvider.setPhoneNo("phoneNo");
        serviceProvider.setCategory(Category.valueOf(category));
    }


    @AfterEach
    void closeUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createServiceProvider_WhenRequestDtoIsNull() {
        assertThrows(MissingRequestBodyException.class, () -> spService.createServiceProvider(null));
    }

    @Test
    void createServiceProvider_WhenEmailIsAlreadyPresent() {
        when(spRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(serviceProvider));
        assertThrows(ValueNotAllowedException.class, () -> spService.createServiceProvider(requestSpDto));
    }

    @Test
    void createServiceProvider_ReturnsNewServiceProvider() throws ValueNotAllowedException, MissingRequestBodyException {
        ResponseSpDto responseSpDto = ResponseSpDto.from(serviceProvider);
        when(spRepository.save(any(ServiceProvider.class)))
                .thenReturn(serviceProvider);
        assertEquals(responseSpDto, spService.createServiceProvider(requestSpDto));
    }

    @Test
    void getServiceProviderById_WhenIdIsEmpty() {
        assertThrows(NotFoundException.class, () -> spService.getServiceProviderById(""), "spId cannot be null");
    }


    @Test
    void getServiceProviderById_WhenIdDoNotExist() {
        when(spRepository.findById(anyString()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> spService.getServiceProviderById(anyString()));
    }

    @Test
    void getServiceProviderById_WhenSPExists() throws NotFoundException {
        String spId = "spid";
        ResponseSpDto responseSpDto = ResponseSpDto.from(serviceProvider);
        when(spRepository.findById(anyString()))
                .thenReturn(Optional.of(serviceProvider));
        assertEquals(responseSpDto, spService.getServiceProviderById(spId));
    }

    @Test
    void getAllServiceProviders_ReturnsEmptyList_WhenNoServiceProviders() {
        List<ServiceProvider> spList = new ArrayList<>();
        doReturn(spList)
                .when(spRepository)
                .findAll();
        List<ResponseSpDto> responseSpDtos = spService.getAllServiceProviders();
        assertEquals(spList.stream().map(ResponseSpDto::from).toList(), responseSpDtos);
    }

    @Test
    void getAllServiceProviders_ReturnList_WhenSPExists() {
        List<ServiceProvider> spList = new ArrayList<>();
        spList.add(new ServiceProvider());
        spList.add(new ServiceProvider());
        spList.add(new ServiceProvider());

        doReturn(spList)
                .when(spRepository)
                .findAll();
        List<ResponseSpDto> actualResult = spService.getAllServiceProviders();
        List<ResponseSpDto> expectedResult = spList.stream().map(ResponseSpDto::from).toList();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateServiceProviderById_WhenSPDoesNotExist() {
        when(spRepository.findById(anyString()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> spService.updateServiceProviderById(requestSpDto, anyString()));
    }

    @Test
    void updateServiceProviderById_WhenRequestDtoIsNull() {
        when(spRepository.findById(anyString()))
                .thenReturn(Optional.of(serviceProvider));
        assertThrows(MissingRequestBodyException.class, () -> spService.updateServiceProviderById(null, ""));
    }

    @Test
    void updateServiceProviderById_WhenSPExists() throws MissingRequestBodyException, NotFoundException {
        ResponseSpDto responseSpDto = ResponseSpDto.from(serviceProvider);
        when(spRepository.findById(anyString()))
                .thenReturn(Optional.of(serviceProvider));
        when(spRepository.save(any(ServiceProvider.class)))
                .thenReturn(serviceProvider);
        assertEquals(responseSpDto, spService.updateServiceProviderById(requestSpDto, anyString()));
    }

    @Test
    void deleteServiceProviderById_WhenSpDoesNotExist() {
        when(spRepository.findById(anyString()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> spService.deleteServiceProviderById(anyString()));
    }

    @Test
    void deleteServiceProviderById_WhenSpExists() throws NotFoundException {
        when(spRepository.findById(anyString()))
                .thenReturn(Optional.of(serviceProvider));
        assertEquals(ResponseSpDto.empty(), spService.deleteServiceProviderById(anyString()));
    }
}
