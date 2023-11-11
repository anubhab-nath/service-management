package com.example.servicemanagement.validations;

import com.example.servicemanagement.models.Category;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

public class SpValidator {
    public static void validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$");
        if(!emailPattern.matcher(email).matches())
            throw new InvalidParameterException("Invalid email provided");
    }

    public static void validatePhoneNo(String phoneNo) {
        Pattern phoneNoPattern = Pattern.compile("^\\+?\\d{10,12}$");
        if(!phoneNoPattern.matcher(phoneNo).matches())
            throw new InvalidParameterException("Invalid phone number provided");
    }

    public static void validateCategory(String category) {
        for(Category existingCategory: Category.values()) {
            if(category.matches(existingCategory.toString()))
                return;
        }
        throw new InvalidParameterException("Invalid category provided");
    }
}
