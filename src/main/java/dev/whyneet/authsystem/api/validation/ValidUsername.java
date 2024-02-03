package dev.whyneet.authsystem.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
@Documented
public @interface ValidUsername {
    String message() default "invalid email provided.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
