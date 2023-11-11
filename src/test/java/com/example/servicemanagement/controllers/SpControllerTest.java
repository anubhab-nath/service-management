package com.example.servicemanagement.controllers;

import com.example.servicemanagement.dtos.RequestSpDto;
import com.example.servicemanagement.dtos.ResponseSpDto;
import com.example.servicemanagement.exceptions.MissingRequestBodyException;
import com.example.servicemanagement.exceptions.NotFoundException;
import com.example.servicemanagement.exceptions.ValueNotAllowedException;
import com.example.servicemanagement.models.Category;
import com.example.servicemanagement.models.ServiceProvider;
import com.example.servicemanagement.services.SpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SpControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpService spService;

    @Autowired
    private ObjectMapper objectMapper;

    private static RequestSpDto requestSpDto;
    private static ResponseSpDto responseSpDto;

    @BeforeAll
    static void init() {
        String category = "DINE_IN";
        requestSpDto = RequestSpDto.builder()
                .email("email")
                .name("name")
                .phoneNo("phoneNo")
                .address("address")
                .category(category)
                .build();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setId("spid");
        serviceProvider.setName("name");
        serviceProvider.setEmail("email");
        serviceProvider.setAddress("address");
        serviceProvider.setPhoneNo("phoneNo");
        serviceProvider.setCategory(Category.valueOf(category));

        responseSpDto = ResponseSpDto.from(serviceProvider);
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void addServiceProvider_ThrowsValueNotAllowedException() throws Exception {
        when(spService.createServiceProvider(any(RequestSpDto.class)))
                .thenThrow(ValueNotAllowedException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/service-provider")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestSpDto)))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void addServiceProvider_ReturnsNewServiceProvider() throws Exception {
        when(spService.createServiceProvider(any(RequestSpDto.class)))
                .thenReturn(responseSpDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/service-provider")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestSpDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void retrieveServiceProvider_ReturnsSpOfGivenSpId() throws Exception {
        when(spService.getServiceProviderById(anyString()))
                .thenReturn(responseSpDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/service-provider/12"))
                .andExpect(status().isOk());
    }

    @Test
    void retrieveAllServiceProviders_WhenNoSP() throws Exception {
        when(spService.getAllServiceProviders())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/service-provider"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").exists())
                .andExpect(content().string("[]"));
    }

    @Test
    void getAllServiceProviders_WhenSpExists() throws Exception {
        List<ResponseSpDto> responseSpDtoList = new ArrayList<>();
        responseSpDtoList.add(responseSpDto);

        when(spService.getAllServiceProviders())
                .thenReturn(responseSpDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/service-provider"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(responseSpDtoList)));
    }

    @Test
    void updateServiceProvider_WhenGivenSpidExists() throws Exception {
        when(spService.updateServiceProviderById(any(RequestSpDto.class), anyString()))
                .thenReturn(responseSpDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/service-provider/12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestSpDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteServiceProvider_WhenGivenSpidExists() throws Exception {
        when(spService.deleteServiceProviderById(anyString()))
                .thenReturn(ResponseSpDto.empty());
        mockMvc.perform(MockMvcRequestBuilders.delete("/service-provider/12"))
                .andExpect(status().isNoContent());
    }
}
