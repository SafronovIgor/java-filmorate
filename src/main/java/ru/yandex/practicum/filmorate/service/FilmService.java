package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FilmService {
    private final Map<Long, Film> films = new HashMap<>();

    public Collection<Film> getFilms() {
        return films.values();
    }

    public void assignNewId(Film obj) {
        obj.setId(ServiceUtil.generateNewId(films));
    }

    public void addUserToMap(Film film) {
        films.put(film.getId(), film);
    }

    public void checkUserExists(Long userId) {
        if (!films.containsKey(userId)) {
            throw new ResourceNotFoundException("Film not found with id: " + userId);
        }
    }
}
