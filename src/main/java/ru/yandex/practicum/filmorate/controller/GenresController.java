package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genres;
import ru.yandex.practicum.filmorate.repository.GenresRepository;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenresController {
    private final GenresRepository genresRepository;

    @GetMapping(produces = "application/json")
    public List<Genres> getGenres() {
        return genresRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Genres getGenre(@PathVariable long id) {
        return genresRepository.findById(id);
    }
}
