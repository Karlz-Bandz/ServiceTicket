package com.ncr.serviceticket.validation.annotations;

import com.ncr.serviceticket.validation.ContactNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContactNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactNumberConstraint {

    String message() default "Provided word must contains only + or numbers.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
