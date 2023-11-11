package com.example.servicemanagement.controllers;

import com.example.servicemanagement.dtos.RequestSpDto;
import com.example.servicemanagement.dtos.ResponseSpDto;
import com.example.servicemanagement.models.Category;
import com.example.servicemanagement.models.ServiceProvider;
import com.example.servicemanagement.services.SpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

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

    private ServiceProvider serviceProvider;

    @BeforeEach
    void setUp() {
        String category = "DINE_IN";
        serviceProvider = new ServiceProvider();
        serviceProvider.setId("spid");
        serviceProvider.setName("name");
        serviceProvider.setEmail("email");
        serviceProvider.setAddress("address");
        serviceProvider.setPhoneNo("phoneNo");
        serviceProvider.setCategory(Category.valueOf(category));
    }

    @Test
    void getAllServiceProviders_WhenNoSP() throws Exception {
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
        responseSpDtoList.add(ResponseSpDto.from(serviceProvider));

        when(spService.getAllServiceProviders())
                .thenReturn(responseSpDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/service-provider"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(responseSpDtoList)));
    }
}
