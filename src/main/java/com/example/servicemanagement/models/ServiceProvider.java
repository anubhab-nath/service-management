package com.example.servicemanagement.models;

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

    private String category;
}
