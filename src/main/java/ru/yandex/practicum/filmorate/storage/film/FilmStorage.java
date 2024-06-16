package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {

    Film getFilmById(long id);

    Collection<Film> getFilms();

    void save(Film obj);

    Film update(Film film);

    void filmExists(long id);
}
