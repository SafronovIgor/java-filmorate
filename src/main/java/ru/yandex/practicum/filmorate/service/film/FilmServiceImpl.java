package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmServiceImpl {

    void addLike(long id, Film film);

    void removeLike(long id, Film film);

    List<Film> getTopPopularFilms(int limit);
}
