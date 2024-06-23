package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.repository.MpaRepository;

@RequiredArgsConstructor
public class RatingCheckerImpl implements ConstraintValidator<RatingChecker, Mpa> {
    private final MpaRepository mpaRepository;

    @Override
    public boolean isValid(Mpa mpa, ConstraintValidatorContext constraintValidatorContext) {
        return mpaRepository.existsById(mpa.getId());
    }
}
