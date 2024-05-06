package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Genres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GenresMapper implements RowMapper<List<Genres>>  {
    @Override
    public List<Genres> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Genres> genres = new ArrayList<>();

        while (rs.next()) {
            Genres genre = new Genres();
            genre.setId(rs.getInt("id"));
            genre.setName(rs.getString("genre_name"));
            genres.add(genre);
        }

        return genres;
    }
}
