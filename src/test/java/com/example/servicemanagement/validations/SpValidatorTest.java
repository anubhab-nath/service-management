package com.example.servicemanagement.validations;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "email",
            "email@domain",
            "34@dsfjd."
    })
    void validateEmail_WhenInvalidEmails(String email) {
        assertThrows(InvalidParameterException.class, () -> SpValidator.validateEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "email@domain.com",
            "any@gamil.com"
    })
    void validateEmail_WhenValidEmails(String email) {
        assertDoesNotThrow(() -> SpValidator.validateEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "7899339014",
            "+917899339014"
    })
    void validatePhoneNo_WhenValidNums(String phoneNum) {
        assertDoesNotThrow(() -> SpValidator.validatePhoneNo(phoneNum));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "372408328",
            "+78090-"
    })
    void validatePhoneNo_WhenInvalidNums(String phoneNum) {
        assertThrows(InvalidParameterException.class, () -> SpValidator.validatePhoneNo(phoneNum));
    }
}
