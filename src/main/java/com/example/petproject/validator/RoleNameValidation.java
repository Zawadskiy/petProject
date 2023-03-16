package com.example.petproject.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleNameValidation {
    String message() default "No such role";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
