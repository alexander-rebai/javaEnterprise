package com.animal.main.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.animal.main.Entity.Accomodation;

public class AccommodationValidator implements Validator {

    private static final int MIN_VALUE = 50;
    private static final int MAX_VALUE = 300;
    private static final int MIN_DIFFERENCE = 50;

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
                    "Pen Code 1 must be between " + MIN_VALUE + " and " + MAX_VALUE);
        }

        if (accomodation.getPenCode2() < MIN_VALUE || accomodation.getPenCode2() > MAX_VALUE) {
            errors.rejectValue("penCode2", "field.outOfRange",
                    "Pen Code 2 must be between " + MIN_VALUE + " and " + MAX_VALUE);
        }

        if (accomodation.getPenCode2() - accomodation.getPenCode1() < MIN_DIFFERENCE) {
            errors.rejectValue("penCode2", "field.invalidDifference",
                    "Pen Code 2 must be at least " + MIN_DIFFERENCE + " greater than Pen Code 1");
        }
    }
}
