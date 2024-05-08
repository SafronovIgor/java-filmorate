package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody @Valid User user) {
        userService.assignNewId(user);
        userService.updateNameFromLoginIfEmpty(user);
        userService.addUserToMap(user);
        return user;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public User updateUser(@RequestBody User user) {
        userService.checkUserExists(user.getId());
        userService.addUserToMap(user);
        return user;
    }
}
