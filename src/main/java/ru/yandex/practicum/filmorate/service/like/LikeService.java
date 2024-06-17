package ru.yandex.practicum.filmorate.service.like;

import java.util.Set;

public interface LikeService {

    void addLikeToFilm(long idFilm, long userId);

    void deleteLikeFromFilm(long idFilm, long userId);

    Set<Long> getLikeFromFilm(long idFilm, long userId);
}
