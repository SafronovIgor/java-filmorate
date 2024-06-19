package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.mapper.GenresMapper;
import ru.yandex.practicum.filmorate.model.Genres;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GenresRepository {
    private final JdbcTemplate jdbcTemplate;
    private final GenresMapper genresMapper;

    public List<Genres> findAll() {
        String sql = """
                SELECT
                    *
                FROM "genre"
                """;
        return jdbcTemplate.query(sql, genresMapper);

    }

    public Genres findById(Long id) {
        if (existsById(id)) {
            String sql = """
                    SELECT
                        *
                    FROM "genre"
                    WHERE id = ?
                    """;
            return jdbcTemplate.queryForObject(sql, genresMapper, id);
        } else throw new ResourceNotFoundException("");
    }

    public List<Genres> findAllById(List<Long> ids) {
        String inSql = ids.stream()
                .map(id -> "?")
                .collect(Collectors.joining(","));

        String query = "SELECT * FROM \"genre\" WHERE id IN (" + inSql + ")";

        return jdbcTemplate.query(query, ids.toArray(), genresMapper);
    }

    public List<Genres> findAllByFilm(Long id) {
        String sql = """
                SELECT g.id, g.genre_name
                FROM "genre" g
                INNER JOIN "film_genre" fg ON fg.genre_id = g.id
                WHERE fg.film_id = ?
                """;

        return jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
            var genre = new Genres();
            genre.setId(rs.getLong("id"));
            genre.setName(rs.getString("genre_name"));
            return genre;
        });
    }

    public boolean existsById(long id) {
        String query = """
                SELECT COUNT(*)
                FROM "genre"
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