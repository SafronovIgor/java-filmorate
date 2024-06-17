package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.mapper.FilmRowMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genres;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FilmRepository {
    private final JdbcTemplate jdbcTemplate;
    private final FilmRowMapper filmRowMapper;
    private final MpaRepository mpaRepository;
    private final GenresRepository genresRepository;

    private static final String SELECT_ALL_FROM_FILM = "SELECT * FROM \"film\"";
    private static final String INSERT_INTO_FILM_GENRE = "INSERT INTO \"film_genre\" (film_id, genre_id) VALUES (?, ?)";

    public Collection<Film> findAll() {
        return jdbcTemplate.query(SELECT_ALL_FROM_FILM, filmRowMapper);
    }

    public Film findById(long id) {
        String query = SELECT_ALL_FROM_FILM + " WHERE id = ?";
        Optional<Film> film = Optional.ofNullable(jdbcTemplate.queryForObject(query, filmRowMapper, id));
        if (film.isPresent()) {
            return film.get();
        } else {
            throw new ResourceNotFoundException("Film with id " + id + " not found");
        }
    }

    public Film save(Film film) {
        if (existsById(film.getId())) {
            update(film);
        } else {
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

                for (Genres genre : film.getGenres()) {
                    addFilmGenre(film.getId(), genre.getId());
                }

                film.setMpa(mpaRepository.findById(film.getMpa().getId()));
                film.setGenres(genresRepository.findAllById(film.getGenres().stream()
                        .map(Genres::getId)
                        .toList()
                ));
            } else {
                throw new RuntimeException("The ID for the new film could not be generated.");
            }
        }
        return film;
    }

    public Film update(Film film) {
        if (!existsById(film.getId())) {
            throw new ResourceNotFoundException("The film could not be found.");
        }

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

        return film;
    }

    public boolean existsById(long id) {
        String query = """
                SELECT count(*)
                FROM "film"
                WHERE id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, id);

        return Optional.ofNullable(count).orElse(0) > 0;
    }

    private void addFilmGenre(long filmId, long genreId) {
        jdbcTemplate.update(INSERT_INTO_FILM_GENRE, filmId, genreId);
    }
}