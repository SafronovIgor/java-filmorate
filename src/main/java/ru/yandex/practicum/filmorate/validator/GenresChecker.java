package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = GenresCheckerImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenresChecker {
    String message() default "Release date should not be earlier than 28 December 1895";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
