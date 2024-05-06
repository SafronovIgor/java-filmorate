package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.GenresMapper;
import ru.yandex.practicum.filmorate.model.Genres;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenresRepository {
    private final JdbcTemplate jdbcTemplate;
    private final GenresMapper genresMapper;

    public List<Genres> findAllById(List<Long> ids) {
        String query = """
                SELECT
                    *
                FROM "genre"
                WHERE id IN (?)
                """;
        return jdbcTemplate.queryForObject(query, genresMapper, ids.toArray());
    }
}
