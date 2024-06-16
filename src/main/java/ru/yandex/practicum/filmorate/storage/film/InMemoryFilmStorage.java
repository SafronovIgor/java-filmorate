package ru.yandex.practicum.filmorate.storage.film;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.util.ServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> filmsInMemory = new HashMap<>();

    @Override
    public Film getFilmById(long id) {
        return filmsInMemory.get(id);
    }

    @Override
    public List<Film> getFilms() {
        return new ArrayList<>(filmsInMemory.values());
    }

    @Override
    public void save(Film obj) {
        long newId = ServiceUtil.generateNewId(filmsInMemory);
        obj.setId(newId);
        filmsInMemory.put(obj.getId(), obj);
    }

    @Override
    public Film update(Film film) {
        filmExists(film.getId());
        filmsInMemory.put(film.getId(), film);
        return film;
    }

    @Override
    public void filmExists(long id) {
        if (!filmsInMemory.containsKey(id)) {
            throw new ResourceNotFoundException("Film not found with id: " + id);
        }
    }
}
