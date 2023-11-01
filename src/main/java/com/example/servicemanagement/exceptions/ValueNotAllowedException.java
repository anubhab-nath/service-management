package com.example.servicemanagement.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ValueNotAllowedException extends Exception {
    private final String errorCode;
    public ValueNotAllowedException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
