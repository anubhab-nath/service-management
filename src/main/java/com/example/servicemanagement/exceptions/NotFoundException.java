package com.example.servicemanagement.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception {
    private final String errorCode;

    public NotFoundException(String errorCode, String message) {
        super(message + " is not found");
        this.errorCode = errorCode;
    }
}
