package com.example.servicemanagement.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestSpDto {
    private String name;
    private String address;
    private String phoneNo;
    private String category;
}
