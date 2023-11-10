package com.example.servicemanagement.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionDto {
    private String errorCode;
    private String message;

    public static ExceptionDto body(String errorCode, String message) {
        return ExceptionDto.builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
