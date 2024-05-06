package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    @Test
    void testValidationFilm() {
        // Тестирование случая, когда Film null
        var exceptionNullFilm = assertThrows(ValidationException.class, () -> FilmController.validationFilm(null));
        assertEquals("Film object is null.", exceptionNullFilm.getMessage());

        // Тестирование случая, когда Name null или пустой
        Film filmNullName = new Film();
        filmNullName.setName(null);
        var exceptionNullName = assertThrows(ValidationException.class, () -> FilmController.validationFilm(filmNullName));
        assertTrue(exceptionNullName.getMessage().contains("Field name cannot be empty or null."));

        Film filmEmptyName = new Film();
        filmEmptyName.setName("");
        var exceptionEmptyName = assertThrows(ValidationException.class, () -> FilmController.validationFilm(filmEmptyName));
        assertTrue(exceptionEmptyName.getMessage().contains("Field name cannot be empty or null."));

        // Тестирование случая, когда Description null или длиннее 200 символов
        Film filmNullDescription = new Film();
        filmNullDescription.setDescription(null);
        var exceptionNullDescription = assertThrows(ValidationException.class, () -> FilmController.validationFilm(filmNullDescription));
        assertTrue(exceptionNullDescription.getMessage().contains("Field description cannot be null."));

        Film filmLongDescription = new Film();
        filmLongDescription.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        var exceptionLongDescription = assertThrows(ValidationException.class, () -> FilmController.validationFilm(filmLongDescription));
        assertTrue(exceptionLongDescription.getMessage().contains("Maximum description length is 200 characters."));

        // Тестирование случая, когда ReleaseDate null
        Film filmNullReleaseDate = new Film();
        filmNullReleaseDate.setReleaseDate(null);
        var exceptionNullReleaseDate = assertThrows(ValidationException.class, () -> FilmController.validationFilm(filmNullReleaseDate));
        assertTrue(exceptionNullReleaseDate.getMessage().contains("Release date must be no earlier than December 28, 1895."));

        // Тестирование случая, когда Duration null
        Film filmNullDuration = new Film();
        filmNullDuration.setDuration(null);
        var exceptionNullDuration = assertThrows(ValidationException.class, () -> FilmController.validationFilm(filmNullDuration));
        assertTrue(exceptionNullDuration.getMessage().contains("Movie duration must be a positive number."));
    }
}