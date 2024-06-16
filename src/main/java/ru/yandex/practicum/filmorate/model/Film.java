package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.validator.ReleaseDateConstraint;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Film {
    private long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Size(max = 200, message = "Description length must be less than or equal to 200 characters")
    private String description;

    @ReleaseDateConstraint
    private LocalDate releaseDate;

    @Positive(message = "Duration must be positive")
    private long duration;

    @NotNull(message = "Mpa cannot be null")
    private Mpa mpa;

    private List<Genres> genres;

    private Set<Long> likes = new HashSet<>();
}