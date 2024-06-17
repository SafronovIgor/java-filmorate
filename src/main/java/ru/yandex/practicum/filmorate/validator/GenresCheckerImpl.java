package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.model.Genres;
import ru.yandex.practicum.filmorate.repository.GenresRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GenresCheckerImpl implements ConstraintValidator<GenresChecker, List<Genres>> {
    private final GenresRepository genresRepository;

    @Override
    public boolean isValid(List<Genres> genres, ConstraintValidatorContext constraintValidatorContext) {
        if (genres == null || genres.isEmpty()) {
            return true;
        }

        List<Long> providedGenreIds = genres.stream()
                .map(Genres::getId)
                .collect(Collectors.toList());

        List<Genres> foundGenres = genresRepository.findAllById(providedGenreIds);

        List<Long> foundGenreIds = foundGenres.stream()
                .map(Genres::getId)
                .toList();

        List<Long> nonExistentGenreIds = providedGenreIds.stream()
                .filter(id -> !foundGenreIds.contains(id))
                .toList();

        if (!nonExistentGenreIds.isEmpty()) {
            String nonExistentGenreIdsString = nonExistentGenreIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",", "[", "]"));
            String errorMessage = "The following genre IDs do not exist: " + nonExistentGenreIdsString;

            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
            return false;
        }

        return true;
    }
}