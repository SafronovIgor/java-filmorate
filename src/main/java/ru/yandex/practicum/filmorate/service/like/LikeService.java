package ru.yandex.practicum.filmorate.service.like;

import ru.yandex.practicum.filmorate.model.Film;

public interface LikeService {

    Film addLikeToFilm(long idFilm, long userId);

    Film deleteLikeFromFilm(long idFilm, long userId);

}
