package com.animal.main.validator;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.animal.main.Entity.Accomodation;

@Component
public class AccommodationValidator implements Validator {

    private static final int MIN_VALUE = 50;
    private static final int MAX_VALUE = 300;
    private static final int MIN_DIFFERENCE = 50;
    private final MessageSource messageSource;

    public AccommodationValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Accomodation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "penCode1", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "penCode2", "field.required");

        Accomodation accomodation = (Accomodation) target;

        if (accomodation.getPenCode1() < MIN_VALUE || accomodation.getPenCode1() > MAX_VALUE) {
            errors.rejectValue("penCode1", "field.outOfRange",
                    new Object[] { MIN_VALUE, MAX_VALUE },
                    messageSource.getMessage("field.outOfRange", null, Locale.getDefault()));
        }

        if (accomodation.getPenCode2() < MIN_VALUE || accomodation.getPenCode2() > MAX_VALUE) {
            errors.rejectValue("penCode2", "field.outOfRange",
                    new Object[] { MIN_VALUE, MAX_VALUE },
                    messageSource.getMessage("field.outOfRange", null, Locale.getDefault()));
        }

        if (accomodation.getPenCode2() - accomodation.getPenCode1() < MIN_DIFFERENCE) {
            errors.rejectValue("penCode2", "field.invalidDifference",
                    new Object[] { MIN_DIFFERENCE, "Pen Code 1" },
                    messageSource.getMessage("field.invalidDifference", null, Locale.getDefault()));
        }
    }
}
