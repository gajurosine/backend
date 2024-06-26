package com.rca.rosinenzambaza.template.v1.annotations.constraints;

import com.rca.rosinenzambaza.template.v1.annotations.ValidUUID;
import com.rca.rosinenzambaza.template.v1.exceptions.InvalidUUIDException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class UuidValidator implements ConstraintValidator<ValidUUID, String> {
    @Override
    public void initialize(ValidUUID constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            UUID.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            throw new InvalidUUIDException("Invalid UUID: " + value, e);
        }
    }
}
