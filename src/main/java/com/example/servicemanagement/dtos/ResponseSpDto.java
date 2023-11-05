package com.example.servicemanagement.dtos;

import com.example.servicemanagement.models.ServiceProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseSpDto {
    private String spId;
    private String name;
    private String address;
    private String phoneNo;
    private String category;

    public static ResponseSpDto empty() {
        return ResponseSpDto.builder().build();
    }

    public static ResponseSpDto from(ServiceProvider savedSavedProvider) {
        return ResponseSpDto.builder()
                .spId(savedSavedProvider.getId())
                .name(savedSavedProvider.getName())
                .address(savedSavedProvider.getAddress())
                .phoneNo(savedSavedProvider.getPhoneNo())
                .category(savedSavedProvider.getCategory())
                .build();
    }
}
