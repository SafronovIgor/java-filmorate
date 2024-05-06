package ru.yandex.practicum.filmorate.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genres;
import ru.yandex.practicum.filmorate.repository.GenresRepository;
import ru.yandex.practicum.filmorate.repository.MpaRepository;

@Component
@RequiredArgsConstructor
public class FilmDTOConverter {
    private final MpaRepository mpaRepository;
    private final GenresRepository genresRepository;

    public Film FilmFromDTO(final FilmDTO filmDTO) {
        Film film = new Film();
        film.setId(filmDTO.getId());
        film.setName(filmDTO.getName());
        film.setDescription(filmDTO.getDescription());
        film.setReleaseDate(filmDTO.getReleaseDate());
        film.setDuration(filmDTO.getDuration());
        film.setMpa(mpaRepository.findById(filmDTO.getMpa().getId()));
        film.setGenres(genresRepository.findAllById(
                filmDTO.getGenres().stream()
                        .map(Genres::getId)
                        .toList()
        ));
        return film;
    }
}
