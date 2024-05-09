package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public Collection<Film> getFilms() {
        return filmService.getFilms();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Film createFilm(@RequestBody @Valid Film film) {
        filmService.assignNewId(film);
        filmService.addUserToMap(film);
        return film;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Film updateFilm(@RequestBody @Valid Film film) {
        filmService.checkUserExists(film.getId());
        filmService.addUserToMap(film);
        return film;
    }
}
