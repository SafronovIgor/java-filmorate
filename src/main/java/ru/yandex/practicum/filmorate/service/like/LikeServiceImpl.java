package ru.yandex.practicum.filmorate.service.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final FilmService filmService;
    private final UserStorage userStorage;

    @Override
    public Film addLikeToFilm(long idFilm, long userId) {
        userStorage.userExists(userId);
        Film film = filmService.getFilm(idFilm);
        film.getLikes().add(userId);
        return film;
    }

    @Override
    public Film deleteLikeFromFilm(long idFilm, long userId) {
        userStorage.userExists(userId);
        Film film = filmService.getFilm(idFilm);
        film.getLikes().remove(userId);
        return film;
    }
}
