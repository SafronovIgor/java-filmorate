package ru.yandex.practicum.filmorate.storage.film;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.repository.FilmRepository;

import java.util.Collection;
import java.util.List;

@Primary
@Component
@RequiredArgsConstructor
public class FilmsDbStorage implements FilmStorage {
    private final FilmRepository filmRepository;

    @Override
    public Film getFilmById(long id) {
        return filmRepository.findById(id);
    }

    @Override
    public Collection<Film> getFilms() {
        return filmRepository.findAll();
    }

    @Override
    public void save(Film obj) {
        filmRepository.save(obj);
    }

    @Override
    public void filmExists(long id) {
        filmRepository.existsById(id);
    }
}