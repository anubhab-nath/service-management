package com.example.servicemanagement.dtos;

import com.example.servicemanagement.models.ServiceProvider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSProviderDto {
    private String id;
    private String name;
    private String address;
    private String phoneNo;
    private String category;

    public static ResponseSProviderDto empty() {
        return new ResponseSProviderDto();
    }

    public static ResponseSProviderDto from(ServiceProvider savedSavedProvider) {
        ResponseSProviderDto sProviderDto = new ResponseSProviderDto();
        sProviderDto.setId(savedSavedProvider.getId());
        sProviderDto.setName(savedSavedProvider.getName());
        sProviderDto.setAddress(savedSavedProvider.getAddress());
        sProviderDto.setPhoneNo(savedSavedProvider.getPhoneNo());
        sProviderDto.setCategory(savedSavedProvider.getCategory());
        return sProviderDto;
    }
}
