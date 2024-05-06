package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.FilmDTO;
import ru.yandex.practicum.filmorate.dto.FilmDTOConverter;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;
    private final FilmDTOConverter filmDTOConverter;

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
    public Film createFilm(@RequestBody @Valid FilmDTO filmDTO) {
        log.info("Creating new film with name: {}", filmDTO.getName());
        return filmService.createFilm(filmDTOConverter.FilmFromDTO(filmDTO));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Film updateFilm(@RequestBody @Valid Film film) {
        log.info("Updating film with id: {}", film.getId());
        return filmService.updateFilm(film);
    }
}
