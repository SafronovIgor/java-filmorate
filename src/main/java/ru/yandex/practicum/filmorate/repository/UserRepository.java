package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.mapper.UserRowMapper;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public User findById(long id) {
        String query = """
                SELECT
                    *
                FROM "user"
                WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(query, userRowMapper, id);
    }

    public List<User> findAll() {
        String query = """
                SELECT
                    *
                FROM "user"
                """;
        return jdbcTemplate.query(query, userRowMapper);
    }

    public User save(User user) {
        if (existsById(user.getId())) {
            update(user);
        } else {
            String query = """
                    INSERT INTO "user" (name, email, login, birthday)
                    VALUES (?, ?, ?, ?)
                    """;

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(query, new String[]{"id"});
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getLogin());
                ps.setDate(4, java.sql.Date.valueOf(user.getBirthday()));
                return ps;
            }, keyHolder);

            Number newID = keyHolder.getKey();

            if (newID != null) {
                user.setId(newID.longValue());
            } else {
                throw new RuntimeException("The ID for the new user could not be generated.");
            }
        }
        return user;
    }

    public void update(User user) {
        if (existsById(user.getId())) {
            String query = """
                    UPDATE "user"
                    SET name = ?, email = ?, login = ?, birthday = ?
                    WHERE id = ?
                    """;
            jdbcTemplate.update(
                    query,
                    user.getName(),
                    user.getEmail(),
                    user.getLogin(),
                    user.getBirthday(),
                    user.getId()
            );
        } else {
            throw new ResourceNotFoundException("The user could not be found.");
        }
    }

    public boolean existsById(long id) {
        String query = """
                SELECT COUNT(*)
                FROM "user"
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
