package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping(produces = "application/json")
    public Collection<Film> getFilms() {
        log.info("Fetching all films");
        return filmService.getFilms();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Film getFilm(@PathVariable Long id) {
        log.info("Fetching film with id: {}", id);
        return filmService.getFilm(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Film createFilm(@RequestBody @Valid Film film) {
        log.info("Creating new film with name: {}", film.getName());
        return filmService.createFilm(film);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Film updateFilm(@RequestBody @Valid Film film) {
        log.info("Updating film with id: {}", film.getId());
        return filmService.updateFilm(film);
    }
}
