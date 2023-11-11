package com.example.servicemanagement.exceptions;

public class MissingRequestBodyException extends Exception {
    public MissingRequestBodyException(String message) {
        super(message);
    }
}
