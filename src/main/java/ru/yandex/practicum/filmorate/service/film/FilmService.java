package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.List;

public interface FilmService {

    Film getFilm(long id);

    Collection<Film> getFilms();

    Film createFilm(Film film);

    Film updateFilm(Film film);

    List<Film> getTopPopularFilms(int limit);
}
