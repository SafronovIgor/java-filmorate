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
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public static void validationUser(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException();
        }

        if (user.getEmail() != null) {
            if (user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
                throw new ValidationException();
            }
        }

        if (user.getLogin() != null) {
            if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
                throw new ValidationException();
            }
        }

        if (user.getName() != null && user.getLogin() != null) {
            if (user.getName().isEmpty()) {
                user.setName(user.getLogin());
            }
        }

        if (user.getBirthday() != null) {
            if (user.getBirthday().isAfter(LocalDate.now())) {
                throw new ValidationException();
            }
        }
    }
}
