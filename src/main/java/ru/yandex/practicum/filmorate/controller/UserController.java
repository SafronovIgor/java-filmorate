package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class UserController {
    private final Map<Long, User> users = new HashMap<>();

    @GetMapping("/users")
    public ResponseEntity<Collection<User>> getUsers() {
        return new ResponseEntity<>(users.values(), HttpStatus.OK);
    }

    @PostMapping(value = "users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            validationUser(user);
            users.put(user.getId(), user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public static void validationUser(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("User object is null.");
        }

        var sb = new StringBuilder();

        if (Objects.equals(user.getEmail(), "null")) {
            sb.append("The field email cannot contain the string value null.").append(System.lineSeparator());
        } else if (user.getEmail() == null || user.getEmail().isEmpty()) {
            sb.append("Email cannot be empty or null.").append(System.lineSeparator());
        } else if (!user.getEmail().contains("@")) {
            sb.append("Email must contain the @ symbol.").append(System.lineSeparator());
        }

        if (Objects.equals(user.getLogin(), "null")) {
            sb.append("The field login cannot contain the string value null.").append(System.lineSeparator());
        } else if (user.getLogin() == null || user.getLogin().isEmpty()) {
            sb.append("Login cannot be empty or null.").append(System.lineSeparator());
        } else if (user.getLogin().contains(" ")) {
            sb.append("Login cannot contain spaces.").append(System.lineSeparator());
        }

        if (Objects.equals(user.getName(), "null")) {
            sb.append("The field name cannot contain the string value null.").append(System.lineSeparator());
        } else if (user.getName() == null || user.getName().isEmpty()) {
            sb.append("Name cannot be empty or null.").append(System.lineSeparator());
        }

        if (user.getBirthday() == null) {
            sb.append("Date of birth cannot be null.").append(System.lineSeparator());
        } else if (user.getBirthday().isAfter(LocalDate.now())) {
            sb.append("Date of birth cannot be in the future.").append(System.lineSeparator());
        }

        if (!sb.isEmpty()) {
            throw new ValidationException(sb.toString(), user);
        }
    }
}
