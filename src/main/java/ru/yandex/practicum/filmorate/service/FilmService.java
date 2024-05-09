package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FilmService implements FilmServiceImpl {
    private final Map<Long, Film> films = new HashMap<>();

    @Override
    public Collection<Film> getFilms() {
        return films.values();
    }

    @Override
    public void assignNewId(Film obj) {
        obj.setId(ServiceUtil.generateNewId(films));
    }

    @Override
    public void addUserToMap(Film film) {
        films.put(film.getId(), film);
    }

    @Override
    public void checkUserExists(long userId) {
        if (!films.containsKey(userId)) {
            throw new ResourceNotFoundException("Film not found with id: " + userId);
        }
    }
}
