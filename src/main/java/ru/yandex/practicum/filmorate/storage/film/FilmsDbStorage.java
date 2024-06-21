package ru.yandex.practicum.filmorate.storage.film;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.repository.FilmRepository;

import java.util.Collection;

@Slf4j
@Component
@RequiredArgsConstructor
public class FilmsDbStorage implements FilmStorage {
    private final FilmRepository filmRepository;

    @Override
    public Film getFilmById(long id) {
        log.info("Fetching Film by ID: {}", id);
        return filmRepository.findById(id);
    }

    @Override
    public Collection<Film> getFilms() {
        log.info("Fetching all Films");
        return filmRepository.findAll();
    }

    @Override
    public Film save(Film obj) {
        log.info("Saving Film: {}", obj.getName());
        return filmRepository.save(obj);
    }

    @Override
    public Film update(Film film) {
        log.info("Updating Film with ID: {}", film.getId());
        return filmRepository.update(film);
    }

    @Override
    public void filmExists(long id) {
        log.info("Checking if Film exists with ID: {}", id);
        filmRepository.existsById(id);
    }
}