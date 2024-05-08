package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private long id;
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Login cannot be blank")
    @Pattern(regexp = "\\S+", message = "Login cannot contain spaces")
    private String login;

    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;
}
