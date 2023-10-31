package com.example.servicemanagement.dtos;

import com.example.servicemanagement.models.ServiceProvider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSPDto {
    private String spId;
    private String name;
    private String address;
    private String phoneNo;
    private String category;

    public static ResponseSPDto empty() {
        return new ResponseSPDto();
    }

    public static ResponseSPDto from(ServiceProvider savedSavedProvider) {
        ResponseSPDto sProviderDto = new ResponseSPDto();
        sProviderDto.setSpId(savedSavedProvider.getId());
        sProviderDto.setName(savedSavedProvider.getName());
        sProviderDto.setAddress(savedSavedProvider.getAddress());
        sProviderDto.setPhoneNo(savedSavedProvider.getPhoneNo());
        sProviderDto.setCategory(savedSavedProvider.getCategory());
        return sProviderDto;
    }
}
