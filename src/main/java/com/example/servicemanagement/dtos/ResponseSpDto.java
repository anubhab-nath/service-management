package com.example.servicemanagement.dtos;

import com.example.servicemanagement.models.Category;
import com.example.servicemanagement.models.ServiceProvider;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class ResponseSpDto {
    private String spId;
    private String email;
    private String name;
    private String address;
    private String phoneNo;
    private String category;

    public static ResponseSpDto empty() {
        return ResponseSpDto.builder().build();
    }

    public static ResponseSpDto from(ServiceProvider savedSavedProvider) {
        Category category = savedSavedProvider.getCategory();
        return ResponseSpDto.builder()
                .spId(savedSavedProvider.getId())
                .email(savedSavedProvider.getEmail())
                .name(savedSavedProvider.getName())
                .address(savedSavedProvider.getAddress())
                .phoneNo(savedSavedProvider.getPhoneNo())
                .category(Objects.isNull(category) ? null : category.toString())
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        ResponseSpDto responseSpDto = (ResponseSpDto) obj;
        boolean spIdEquals = (spId == null && responseSpDto.spId == null)
                || (spId != null && spId.equals(responseSpDto.spId));
        boolean emailEquals = (email == null && responseSpDto.email == null)
                || (email != null && email.equals(responseSpDto.email));
        boolean nameEquals = (name == null && responseSpDto.name == null)
                || (name != null && name.equals(responseSpDto.name));
        boolean addressEquals = (address == null && responseSpDto.address == null)
                || (address != null && address.equals(responseSpDto.address));
        boolean phoneNoEquals = (phoneNo == null && responseSpDto.phoneNo == null)
                || (phoneNo != null && phoneNo.equals(responseSpDto.phoneNo));
        boolean categoryEquals = (category == null && responseSpDto.category == null)
                || (category != null && category.equals(responseSpDto.category));
        return spIdEquals && emailEquals && nameEquals && addressEquals && phoneNoEquals && categoryEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spId, email, name, address, phoneNo, category);
    }
}
