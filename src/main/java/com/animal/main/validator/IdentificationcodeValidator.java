package com.animal.main.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.animal.main.Service.AdminService;

@Component
public class IdentificationcodeValidator implements Validator {

    private final AdminService adminService;
    private final MessageSource messageSource;

    @Autowired
    public IdentificationcodeValidator(AdminService adminService, MessageSource messageSource) {
        this.adminService = adminService;
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String value = (String) target;

        if (value == null || value.isEmpty()) {
            errors.rejectValue("identification_code", "id.empty", messageSource.getMessage("id.empty", null, null));
            return;
        }

        if (value.length() != 10) {
            errors.rejectValue("identification_code", "id.length", messageSource.getMessage("id.length", null, null));
            return;
        }

        String firstThreeChars = value.substring(0, 3);
        if (!Pattern.matches("^[a-zA-Z]{3}$", firstThreeChars)) {
            errors.rejectValue("identification_code", "id.invalidFormatLetters",
                    messageSource.getMessage("id.invalidFormatLetters", null, null));
            return;
        }

        String nextSevenChars = value.substring(3, 10);
        if (!Pattern.matches("^\\d{7}$", nextSevenChars)) {
            errors.rejectValue("identification_code", "id.invalidFormatDigits",
                    messageSource.getMessage("id.invalidFormatDigits", null, null));
            return;
        }

        try {
            int firstFiveDigits = Integer.parseInt(value.substring(3, 5));
            int lastTwoDigits = firstFiveDigits % 3;
            String lastTwoDigitsString = value.substring(8);

            if (Integer.parseInt(lastTwoDigitsString) != lastTwoDigits) {
                errors.rejectValue("identification_code", "id.invalidLastDigits",
                        messageSource.getMessage("id.invalidLastDigits", null, null));
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("identification_code", "id.invalidFormat",
                    messageSource.getMessage("id.invalidFormat", null, null));
        }

        if (adminService.IDF_CODE_already_exists(value) != null) {
            errors.rejectValue("identification_code", "id.alreadyExists",
                    messageSource.getMessage("id.alreadyExists", null, null));
        }

    }
}
