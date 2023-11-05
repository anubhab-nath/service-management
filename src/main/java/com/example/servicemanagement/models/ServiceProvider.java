package com.example.servicemanagement.models;

import com.example.servicemanagement.dtos.RequestSpDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "service-providers")
public class ServiceProvider {
    @Id
    private String id;

    private String name;
    private String address;
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private Category category;

    public static ServiceProvider from(RequestSpDto requestSpDto) {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName(requestSpDto.getName());
        serviceProvider.setAddress(requestSpDto.getAddress());
        serviceProvider.setPhoneNo(requestSpDto.getPhoneNo());
        serviceProvider.setCategory(Category.valueOf(requestSpDto.getCategory()));
        return serviceProvider;
    }

    public ServiceProvider updateFrom(RequestSpDto requestSpDto) {
        this.setName(requestSpDto.getName());
        this.setAddress(requestSpDto.getAddress());
        this.setPhoneNo(requestSpDto.getPhoneNo());
        this.setCategory(Category.valueOf(requestSpDto.getCategory()));
        return this;
    }
}
