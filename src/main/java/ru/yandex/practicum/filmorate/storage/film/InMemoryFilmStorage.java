package ru.yandex.practicum.filmorate.storage.film;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.util.ServiceUtil;

import java.util.*;

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
    public void setNewId(Film obj) {
        long newId = ServiceUtil.generateNewId(filmsInMemory);
        obj.setId(newId);
    }

    @Override
    public void addFilm(Film obj) {
        filmsInMemory.put(obj.getId(), obj);
    }

    @Override
    public boolean filmExists(long id) {
        return filmsInMemory.containsKey(id);
    }
}
