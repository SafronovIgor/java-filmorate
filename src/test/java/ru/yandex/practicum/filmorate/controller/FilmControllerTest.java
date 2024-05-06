package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    @Test
    void testValidationFilm() {
        // Тестирование случая, когда Film null
        assertThrows(ValidationException.class, () -> FilmController.validationFilm(null));

        // Тестирование случая, когда Name пустой
        Film filmEmptyName = new Film();
        filmEmptyName.setName("");
        assertThrows(ValidationException.class, () -> FilmController.validationFilm(filmEmptyName));

        // Тестирование случая, когда Description длиннее 200 символов
        Film filmLongDescription = new Film();
        filmLongDescription.setDescription("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS" +
                "SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS" +
                "SSSSSSSSSSSSSSSSSSSS");
        assertThrows(ValidationException.class, () -> FilmController.validationFilm(filmLongDescription));

        // Тестирование случая, когда ReleaseDate дата раньше 1895-12-28
        Film filmEarlyReleaseDate = new Film();
        filmEarlyReleaseDate.setReleaseDate(LocalDate.parse("1895-01-01"));
        assertThrows(ValidationException.class, () -> FilmController.validationFilm(filmEarlyReleaseDate));

        // Тестирование случая, когда Duration отрицательная
        Film filmNegativeDuration = new Film();
        filmNegativeDuration.setDuration(Duration.ZERO.minusDays(1));
        assertThrows(ValidationException.class, () -> FilmController.validationFilm(filmNegativeDuration));
    }
}