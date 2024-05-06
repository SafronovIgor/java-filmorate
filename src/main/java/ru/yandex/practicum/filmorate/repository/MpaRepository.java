package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class MpaRepository {
    private final JdbcTemplate jdbcTemplate;
    private final MpaMapper mpaMapper;

    public Mpa[] findAllById(Collection<Integer> ids) {
        String query = """
                SELECT
                    *
                FROM "mpa_rating"
                WHERE id IN (?)
                """;
        return new Mpa[]{jdbcTemplate.queryForObject(query, mpaMapper, ids.toArray())};
    }

    public Mpa findById(Long id) {
        String query = """
                SELECT
                    *
                FROM "mpa_rating"
                WHERE id =?
                """;
        return jdbcTemplate.queryForObject(query, mpaMapper, id);
    }
}
