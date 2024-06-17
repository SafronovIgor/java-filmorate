package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Genres;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GenresMapper implements RowMapper<Genres> {
    @Override
    public Genres mapRow(ResultSet rs, int rowNum) throws SQLException {

        Genres genre = new Genres();
        genre.setId(rs.getLong("id"));
        genre.setName(rs.getString("genre_name"));

        return genre;
    }
}