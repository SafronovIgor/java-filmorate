package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = GenresCheckerImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenresChecker {
    String message() default "Provided genres list contains non-existent IDs";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
