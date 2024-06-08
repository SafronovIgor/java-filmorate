package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private long id;

    private String name;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Login cannot be blank")
    @Pattern(regexp = "\\S+", message = "Login cannot contain spaces")
    private String login;

    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;

    private Set<Long> friends = new HashSet<>();
}
