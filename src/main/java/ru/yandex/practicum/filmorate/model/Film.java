package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.validator.ReleaseDateConstraint;

import java.time.LocalDate;

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
}