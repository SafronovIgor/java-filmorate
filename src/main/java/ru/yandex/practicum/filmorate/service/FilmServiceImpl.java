package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmServiceImpl {

    Collection<Film> getFilms();

    void assignNewId(Film obj);

    void addUserToMap(Film obj);

    void checkUserExists(long id);
}
