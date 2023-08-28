package com.animal.main.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.animal.main.Service.AdminService;

@Component
public class IdentificationcodeValidator implements Validator {

    private final AdminService adminService;

    @Autowired
    public IdentificationcodeValidator(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String value = (String) target;

        System.out.println(value);

        if (value == null || value.isEmpty()) {
            System.out.println("empty");
            errors.rejectValue("identification_code", "empty", "Identificatiecode must not be empty");
            return;
        }

        if (value.length() != 10) {
            System.out.println("length");
            errors.rejectValue("identification_code", "length",
                    "Identificatiecode must consist of exactly 10 characters");
            return;
        }

        // Check if the first 3 characters are letters
        String firstThreeChars = value.substring(0, 3);
        if (!Pattern.matches("^[a-zA-Z]{3}$", firstThreeChars)) {
            System.out.println("first chars");
            errors.rejectValue("identification_code", "invalidFormat", "The first 3 characters must be letters");
            return;
        }

        // Check if the next 7 characters are digits
        String nextSevenChars = value.substring(3, 10);
        if (!Pattern.matches("^\\d{7}$", nextSevenChars)) {
            System.out.println("next chars");
            errors.rejectValue("identification_code", "invalidFormat", "The next 7 characters must be digits");
            return;
        }

        // Calculate the last 2 digits based on the first 5 digits
        try {
            int firstFiveDigits = Integer.parseInt(value.substring(3, 5));
            int lastTwoDigits = firstFiveDigits % 3;

            // Check if the last 2 digits match the calculated value
            String lastTwoDigitsString = value.substring(8);
            if (Integer.parseInt(lastTwoDigitsString) != lastTwoDigits) {
                System.out.println("final chars");
                errors.rejectValue("identification_code", "invalidFormat", "The last 2 digits are incorrect");
            }
        } catch (NumberFormatException e) {
            // Handle the parsing error
            System.out.println("Error parsing digits: " + e.getMessage());
            errors.rejectValue("identification_code", "invalidFormat", "Invalid format for digits");
        }

        if (adminService.IDF_CODE_already_exists(value) != null) {
            System.out.println("idcode exists");
            errors.rejectValue("identification_code", "already exists", "Identification code already exists");
        }

    }
}
