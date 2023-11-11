package com.example.servicemanagement.models;

import com.example.servicemanagement.dtos.RequestSpDto;
import com.example.servicemanagement.validations.SpValidator;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "service-providers")
public class ServiceProvider {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String name;
    private String address;
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private Category category;

    public static ServiceProvider from(RequestSpDto requestSpDto) {
        SpValidator.validateEmail(requestSpDto.getEmail());
        SpValidator.validatePhoneNo(requestSpDto.getPhoneNo());
        SpValidator.validateCategory(requestSpDto.getCategory());

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setEmail(requestSpDto.getEmail());
        serviceProvider.setName(requestSpDto.getName());
        serviceProvider.setAddress(requestSpDto.getAddress());
        serviceProvider.setPhoneNo(requestSpDto.getPhoneNo());
        serviceProvider.setCategory(Category.valueOf(requestSpDto.getCategory()));
        return serviceProvider;
    }

    public ServiceProvider updateFrom(RequestSpDto requestSpDto) {
        this.setEmail(requestSpDto.getEmail());
        this.setName(requestSpDto.getName());
        this.setAddress(requestSpDto.getAddress());
        this.setPhoneNo(requestSpDto.getPhoneNo());
        this.setCategory(Category.valueOf(requestSpDto.getCategory()));
        return this;
    }
}
