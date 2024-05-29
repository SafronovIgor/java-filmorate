package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilmServiceTest {

    private FilmService filmService;

    @BeforeEach
    void setUp() {
        filmService = new FilmService(new InMemoryFilmStorage());
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

        filmService.addFilmToMap(film);

        Collection<Film> films = filmService.getFilms();
        assertEquals(1, films.size());
        assertTrue(films.contains(film));
    }

    @Test
    void testCheckUserExists() {
        assertThrows(ResourceNotFoundException.class, () -> filmService.checkFilmExists(1L));

        var film = new Film();
        filmService.assignNewId(film);
        filmService.addFilmToMap(film);

        assertDoesNotThrow(() -> filmService.checkFilmExists(1L));
    }

    @Test
    void testAddLike() {
        var film = new Film();
        filmService.assignNewId(film);
        filmService.addFilmToMap(film);

        long beforeLikes = film.getLikes().size();
        filmService.addLike(1L, film);
        long afterLikes = film.getLikes().size();

        assertEquals(beforeLikes + 1, afterLikes);
        assertTrue(film.getLikes().contains(1L));
    }

    @Test
    void testRemoveLike() {
        var film = new Film();
        filmService.assignNewId(film);
        filmService.addFilmToMap(film);
        filmService.addLike(1L, film);

        assertTrue(film.getLikes().contains(1L));

        filmService.removeLike(1L, film);
        assertFalse(film.getLikes().contains(1L));
    }

    @Test
    void testGetTopPopularFilms() {
        for (int i = 0; i < 11; i++) {

            var film = new Film();
            filmService.assignNewId(film);

            for (int j = 0; j < i; j++) {
                filmService.addLike(j, film);
            }

            filmService.addFilmToMap(film);
        }

        List<Film> films = filmService.getTopPopularFilms(10);
        assertEquals(10, films.size());
    }
}
