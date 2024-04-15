package com.ncr.serviceticket.validation.annotations;

import com.ncr.serviceticket.validation.UpperCaseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UpperCaseValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UpperCaseConstraint {

    String message() default "Provided word must have only upper-case characters.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
