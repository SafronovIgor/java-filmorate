package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.model.Mpa;

@Repository
@RequiredArgsConstructor
public class MpaRepository {
    private final JdbcTemplate jdbcTemplate;
    private final MpaMapper mpaMapper;

    public Mpa findById(Long id) {
        String query = """
                SELECT
                    *
                FROM "mpa_rating"
                WHERE id =?
                """;
        return jdbcTemplate.queryForObject(query, mpaMapper, id);
    }

    public boolean existsById(long id) {
        String query = """
                SELECT COUNT(*)
                FROM "mpa_rating"
                WHERE id = ?
                """;
        Long count = jdbcTemplate.queryForObject(
                query,
                (rs, rowNum) -> rs.getLong(1),
                id
        );
        return count != null && count > 0;
    }
}
