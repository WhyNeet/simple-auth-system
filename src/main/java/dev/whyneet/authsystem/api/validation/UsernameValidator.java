package dev.whyneet.authsystem.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {
    private final Pattern pattern = Pattern.compile(REGEX);
    private static final String REGEX = "^[a-zA-Z0-9._-]{2,}$";

    @Override
    public void initialize(ValidUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return validate(s);
    }

    private boolean validate(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
