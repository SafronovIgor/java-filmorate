package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.GenresMapper;
import ru.yandex.practicum.filmorate.model.Genres;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GenresRepository {
    private final JdbcTemplate jdbcTemplate;
    private final GenresMapper genresMapper;

    public List<Genres> findAllById(List<Long> ids) {
        String inSql = ids.stream()
                .map(id -> "?")
                .collect(Collectors.joining(","));

        String query = "SELECT * FROM \"genre\" WHERE id IN (" + inSql + ")";

        return jdbcTemplate.query(query, ids.toArray(), genresMapper);
    }
}