package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "films", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film) {
        films.put(film.getId(), film);
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    public static void validationFilm(Film film) throws ValidationException {
        if (film == null) {
            throw new ValidationException("Film object is null.");
        }

        var sb = new StringBuilder();

        if (Objects.equals(film.getName(), "null")) {
            sb.append("The field name cannot contain the string value null.").append(System.lineSeparator());
        } else if (film.getName() == null || film.getName().isEmpty()) {
            sb.append("Field name cannot be empty or null.").append(System.lineSeparator());
        }

        if (Objects.equals(film.getDescription(), "null")) {
            sb.append("The field description cannot contain the string value null.").append(System.lineSeparator());
        } else if (film.getDescription() == null) {
            sb.append("Field description cannot be null.").append(System.lineSeparator());
        } else if (film.getDescription().length() > 200) {
            sb.append("Maximum description length is 200 characters.").append(System.lineSeparator());
        }

        if (film.getReleaseDate() == null || film.getReleaseDate().isBefore(Instant.parse("1895-12-28T00:00:00Z"))) {
            sb.append("Release date must be no earlier than December 28, 1895.").append(System.lineSeparator());
        }

        if (film.getDuration() == null || film.getDuration().isNegative()) {
            sb.append("Movie duration must be a positive number.").append(System.lineSeparator());
        }

        if (!sb.isEmpty()) {
            throw new ValidationException(sb.toString(), film);
        }
    }
}
