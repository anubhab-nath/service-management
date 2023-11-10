package com.example.servicemanagement.controllers;

import com.example.servicemanagement.dtos.RequestSpDto;
import com.example.servicemanagement.dtos.ResponseSpDto;
import com.example.servicemanagement.models.ServiceProvider;
import com.example.servicemanagement.services.SpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SpControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private SpController spController;

    @Mock
    private SpService spService;

    private ServiceProvider serviceProvider;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {}

    @Test
    public void addServiceProvider_test() throws Exception {
        RequestSpDto requestSpDto = RequestSpDto.builder()
                .email("email")
                .name("name")
                .phoneNo("phoneNo")
                .address("address")
                .category("category")
                .build();
        ResponseSpDto responseSpDto = ResponseSpDto.builder()
                .spId("spId")
                .email("emailId")
                .name("name")
                .phoneNo("phoneNo")
                .address("address")
                .category("category")
                .build();

        Mockito.doReturn(responseSpDto).when(spService).createServiceProvider(Mockito.any());
        mockMvc = MockMvcBuilders.standaloneSetup(spController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/service-providers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestSpDto)))
                .andExpect(status().is4xxClientError());
    }
}
