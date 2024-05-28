package ru.yandex.practicum.filmorate.service.film;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService implements FilmServiceImpl {
    private final FilmStorage filmStorage;

    public FilmService(@Qualifier("inMemoryFilmStorage") FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Collection<Film> getFilms() {
        return filmStorage.getFilms();
    }

    public Film getFilm(long id) {
        return filmStorage.getFilmById(id);
    }

    public void assignNewId(Film film) {
        filmStorage.setNewId(film);
    }

    public void addFilmToMap(Film film) {
        filmStorage.addFilm(film);
    }

    public void checkFilmExists(long id) {
        if (!filmStorage.filmExists(id)) {
            throw new ResourceNotFoundException("Film not found with id: " + id);
        }
    }

    @Override
    public void addLike(long id, Film film) {
        film.getLikes().add(id);
    }

    @Override
    public void removeLike(long id, Film film) {
        film.getLikes().remove(id);
    }

    public List<Film> getTopPopularFilms(int limit) {
        return filmStorage.getFilms().stream()
                .sorted((film1, film2) -> Integer.compare(film2.getLikes().size(), film1.getLikes().size()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
