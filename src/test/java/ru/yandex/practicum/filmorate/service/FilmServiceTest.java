package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class FilmServiceTest {

    private FilmService filmService;

    @BeforeEach
    void setUp() {
        filmService = new FilmService();
    }

    @Test
    void testGetFilms() {
        Collection<Film> films = filmService.getFilms();
        assertNotNull(films);
        assertTrue(films.isEmpty());
    }

    @Test
    void testAssignNewId() {
        var film = new Film();
        filmService.assignNewId(film);
        assertNotEquals(0, film.getId());
    }

    @Test
    void testAddUserToMap() {
        var film = new Film();
        filmService.assignNewId(film);
        film.setName("Test Film");
        film.setDescription("Test Description");
        film.setReleaseDate(LocalDate.now());
        film.setDuration(120);

        filmService.addUserToMap(film);

        Collection<Film> films = filmService.getFilms();
        assertEquals(1, films.size());
        assertTrue(films.contains(film));
    }

    @Test
    void testCheckUserExists() {
        assertThrows(ResourceNotFoundException.class, () -> filmService.checkUserExists(1L));

        var film = new Film();
        filmService.assignNewId(film);
        filmService.addUserToMap(film);

        assertDoesNotThrow(() -> filmService.checkUserExists(1L));
    }
}
