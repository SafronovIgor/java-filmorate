package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(produces = "application/json")
    public Collection<User> getUsers() {
        log.info("Fetching all users.");
        return userService.getUsers();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public User getUser(@PathVariable Long id) {
        log.info("Fetching user with id: {}", id);
        return userService.getUser(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody @Valid User user) {
        log.info("Creating user with details: {}", user);
        return userService.createUser(user);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public User updateUser(@RequestBody @Valid User user) {
        log.info("Updating user with details: {}", user);
        return userService.updateUser(user);
    }
}
