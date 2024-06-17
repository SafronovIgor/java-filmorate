package ru.yandex.practicum.filmorate.service.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.repository.LikeRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    @Override
    public void addLikeToFilm(long idFilm, long userId) {
        likeRepository.addLikeToFilm(idFilm, userId);
    }

    @Override
    public void deleteLikeFromFilm(long idFilm, long userId) {
        likeRepository.removeLikeFromFilm(idFilm, userId);
    }

    @Override
    public Set<Long> getLikeFromFilm(long idFilm, long userId) {
        return likeRepository.getLikeFromFilm(idFilm);
    }
}
