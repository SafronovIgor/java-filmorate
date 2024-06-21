package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = RatingCheckerImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RatingChecker {
    String message() default "Unable to find rating by id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
