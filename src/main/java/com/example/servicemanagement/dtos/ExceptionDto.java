package com.example.servicemanagement.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDto {
    private String errorCode;
    private String message;

    public static ExceptionDto body(String errorCode, String message) {
        ExceptionDto dto = new ExceptionDto();
        dto.setErrorCode(errorCode);
        dto.setMessage(message);
        return dto;
    }
}
