package ru.yandex.practicum.filmorate.service.like;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.service.film.FilmServiceImpl;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LikeServiceImplTest {
    FilmService filmService;
    FilmStorage filmStorage;
    LikeService likeService;
    UserStorage userStorage;

    @BeforeEach
    public void setUp() {
        filmStorage = new InMemoryFilmStorage();
        filmService = new FilmServiceImpl(filmStorage);
        userStorage = new InMemoryUserStorage();
        likeService = new LikeServiceImpl(filmService, userStorage);

    }

    @Test
    void addLikeToFilm() {
        User user = new User();
        userStorage.setNewId(user);
        userStorage.addUser(user);

        Film film = new Film();
        filmStorage.setNewId(film);
        filmStorage.addFilm(film);

        likeService.addLikeToFilm(film.getId(), user.getId());

        int countLikes = film.getLikes().size();

        assertEquals(1, countLikes);

    }

    @Test
    void deleteLikeFromFilm() {
        User user = new User();
        userStorage.setNewId(user);
        userStorage.addUser(user);

        Film film = new Film();
        filmStorage.setNewId(film);
        filmStorage.addFilm(film);

        likeService.addLikeToFilm(film.getId(), user.getId());
        likeService.deleteLikeFromFilm(film.getId(), user.getId());

        assertTrue(film.getLikes().isEmpty());

    }
}