package com.ncr.serviceticket.validation;

import com.ncr.serviceticket.validation.annotations.UpperCaseConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpperCaseValidator implements ConstraintValidator<UpperCaseConstraint, String> {

    @Override
    public void initialize(UpperCaseConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String word, ConstraintValidatorContext constraintValidatorContext) {
        return word != null && word.equals(word.toUpperCase());
    }
}
