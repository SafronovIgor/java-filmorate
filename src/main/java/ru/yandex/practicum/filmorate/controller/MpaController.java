package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.repository.MpaRepository;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mpa")
@RequiredArgsConstructor
public class MpaController {
    private final MpaRepository mpaRepository;

    @GetMapping(produces = "application/json")
    public List<Mpa> getMpaList() {
        log.info("Fetching all Mpa");
        return mpaRepository.getMpaList();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Mpa getMpa(@PathVariable long id) {
        log.info("Fetching Mpa with id: {}", id);
        return mpaRepository.getMpaById(id);
    }
}
