package com.example.petproject.validator;

import com.example.petproject.model.ERole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class RoleNameValidator implements ConstraintValidator<RoleNameValidation, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(ERole.values()).anyMatch(role -> role.name().equals(s));
    }

    @Override
    public void initialize(RoleNameValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
