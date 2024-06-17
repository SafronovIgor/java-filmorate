package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.service.like.LikeService;

@Slf4j
@RestController
@RequestMapping("/films/{filmId}/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PutMapping(path = "/{userId}", produces = "application/json")
    public void addLikeToFilm(@PathVariable Long filmId, @PathVariable Long userId) {
        log.info("Adding like to film with id: {} from user with id: {}", filmId, userId);
        likeService.addLikeToFilm(filmId, userId);
    }

    @DeleteMapping(path = "/{userId}", produces = "application/json")
    public void deleteLikeFromFilm(@PathVariable Long filmId, @PathVariable Long userId) {
        log.info("Deleting like from film with id: {} from user with id: {}", filmId, userId);
        likeService.deleteLikeFromFilm(filmId, userId);
    }

}
