package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;

import java.util.HashSet;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class LikeRepository {
    private final JdbcTemplate jdbcTemplate;

    public void addLikeToFilm(long filmId, long userId) {
        String query = """
                INSERT INTO "likes" (user_id, film_id)
                VALUES (?, ?)
                """;
        jdbcTemplate.update(
                query,
                userId,
                filmId
        );
    }

    public void removeLikeFromFilm(long filmId, long userId) {
        String query = """
                DELETE FROM "likes"
                WHERE user_id = ? AND film_id = ?
                """;
        int affectedRows = jdbcTemplate.update(
                query,
                userId,
                filmId
        );
        if (affectedRows == 0) {
            throw new ResourceNotFoundException("No such like from this user to this film was found.");
        }
    }

    public Set<Long> getLikeFromFilm(long idFilm) {
        String query = """
                SELECT user_id
                FROM "likes"
                WHERE film_id = ?
                """;

        Set<Long> likeFromFilm = new HashSet<>();

        jdbcTemplate.query(
                query,
                new Object[]{idFilm},
                (rs, rowNum) -> likeFromFilm.add(rs.getLong("user_id"))
        );

        return likeFromFilm;
    }

}
