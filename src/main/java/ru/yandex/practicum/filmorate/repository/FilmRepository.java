package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.mapper.FilmRowMapper;
import ru.yandex.practicum.filmorate.model.Film;

import java.sql.PreparedStatement;
import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class FilmRepository {
    private final JdbcTemplate jdbcTemplate;
    private final FilmRowMapper filmRowMapper;

    public Collection<Film> findAll() {
        String query = """
                SELECT
                    *
                FROM "film"
                """;
        return jdbcTemplate.query(query, filmRowMapper);
    }

    public Film findById(long id) {
        String query = """
                SELECT
                    *
                FROM "film"
                WHERE id = ?    
                """;
        return jdbcTemplate.queryForObject(query, filmRowMapper, id);
    }

    public Film save(Film film) {
        String query = """
                INSERT INTO "film" (name, description, release_date, duration, mpa_rating_id)
                VALUES (?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setString(1, film.getName());
            ps.setString(2, film.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(film.getReleaseDate()));
            ps.setLong(4, film.getDuration());
            ps.setLong(5, film.getMpa().getId());
            return ps;
        }, keyHolder);

        Number newID = keyHolder.getKey();

        if (newID != null) {
            film.setId(newID.longValue());
        } else {
            throw new RuntimeException("The ID for the new film could not be generated.");
        }
        return film;
    }

    public Film update(Film film) {
        if (existsById(film.getId())) {
            String query = """
                    UPDATE "film"
                    SET name = ?, description = ?, release_date = ?, duration = ?, mpa_rating_id = ?
                    WHERE id = ?
                    """;
            jdbcTemplate.update(
                    query,
                    film.getName(),
                    film.getDescription(),
                    film.getReleaseDate(),
                    film.getDuration(),
                    film.getMpa().getId(),
                    film.getId()
            );
        } else {
            throw new ResourceNotFoundException("The film could not be found.");
        }
        return film;
    }

    public boolean existsById(long id) {
        String query = """
                SELECT COUNT(*)
                FROM "film"
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
