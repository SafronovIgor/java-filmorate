package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping(produces = "application/json")
    public Collection<Film> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Film getFilm(@PathVariable Long id) {
        return filmService.getFilm(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Film createFilm(@RequestBody @Valid Film film) {
        filmService.assignNewId(film);
        filmService.addFilmToMap(film);
        return film;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Film updateFilm(@RequestBody @Valid Film film) {
        filmService.checkFilmExists(film.getId());
        filmService.addFilmToMap(film);
        return film;
    }

    @PutMapping(path = "{id}/like/{userId}", produces = "application/json")
    public Film setLikeToFilm(@PathVariable Long id, @PathVariable Long userId) {
        Film film = filmService.getFilm(id);
        filmService.addLike(userId, film);
        return film;
    }

    @DeleteMapping(path = "{id}/like/{userId}")
    public Film deleteLikeFromFilm(@PathVariable Long id, @PathVariable Long userId) {
        Film film = filmService.getFilm(id);
        filmService.removeLike(userId, film);
        return film;
    }

    @GetMapping(path = "/popular", produces = "application/json")
    public List<Film> getPopularFilms(@RequestParam(name = "count", defaultValue = "10") int count) {
        return filmService.getTopPopularFilms(count);
    }
}
