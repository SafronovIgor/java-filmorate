package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController()
public class FilmController {
    private final Map<Long, Film> films = new HashMap<>();

    @GetMapping("/films")
    public ResponseEntity<Collection<Film>> getFilms() {
        return new ResponseEntity<>(films.values(), HttpStatus.OK);
    }

    @PostMapping(value = "films", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createFilm(@RequestBody Film film) {
        try {
            validationFilm(film);
            films.put(film.getId(), film);
            return new ResponseEntity<>(film, HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(film, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "films", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film) {
        if (!films.containsKey(film.getId())) {
            return new ResponseEntity<>(film, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        films.put(film.getId(), film);
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    public static void validationFilm(Film film) throws ValidationException {
        if (film == null) {
            throw new ValidationException();
        }

        if (film.getName() != null) {
            if (film.getName().isEmpty()) {
                throw new ValidationException();
            }
        }

        if (film.getDescription() != null) {
            if (film.getDescription().length() > 200) {
                throw new ValidationException();
            }
        }

        if (film.getReleaseDate() != null) {
            if (film.getReleaseDate().isBefore(LocalDate.parse("1895-12-28"))) {
                throw new ValidationException();
            }
        }

        if (film.getDuration() < 0) {
            throw new ValidationException();
        }
    }
}
