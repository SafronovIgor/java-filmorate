package ru.yandex.practicum.filmorate.storage.film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryFilmStorageTest {
    FilmStorage filmStorage;

    @BeforeEach
    void setUp() {
        filmStorage = new InMemoryFilmStorage();
        IntStream.range(0, 10).forEach(i -> {
            Film film = new Film();
            filmStorage.setNewId(film);
            filmStorage.addFilm(film);
        });
    }

    @Test
    void getFilmById() {
        Film expectedFilm = new Film();
        filmStorage.addFilm(expectedFilm);
        Film actualFilm = filmStorage.getFilmById(expectedFilm.getId());
        assertEquals(expectedFilm, actualFilm, "The retrieved film is not as expected.");
    }

    @Test
    void getFilms() {
        assertEquals(10, filmStorage.getFilms().size(), "There should be 10 films.");
    }

    @Test
    void setNewId() {
        Film filmById = filmStorage.getFilmById(1);
        filmStorage.setNewId(filmById);
        assertNotEquals(0, filmById.getId(), "The film id is not as expected.");
    }

    @Test
    void addFilm() {
        int size = filmStorage.getFilms().size();
        filmStorage.addFilm(new Film());
        int size2 = filmStorage.getFilms().size();
        assertNotEquals(size, size2, "The film id is not as expected.");
    }

    @Test
    void filmExists() {
        assertThrowsExactly(
                ResourceNotFoundException.class,
                () -> filmStorage.filmExists(999),
                "Expected filmExists() to throw an ResourceNotFoundException, but it didn't"
        );
    }
}