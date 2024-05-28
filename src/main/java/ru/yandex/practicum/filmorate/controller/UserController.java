package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(produces = "application/json")
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody @Valid User user) {
        userService.assignNewId(user);
        userService.updateNameFromLoginIfEmpty(user);
        userService.addUserToMap(user);
        return user;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public User updateUser(@RequestBody @Valid User user) {
        userService.checkUserExists(user.getId());
        userService.addUserToMap(user);
        return user;
    }

    @GetMapping(path = "/{id}/friends")
    public List<User> getFriends(@PathVariable Long id) {
        userService.checkUserExists(id);
        return userService
                .getUser(id)
                .getFriends()
                .stream()
                .map(userService::getUser)
                .toList();
    }

    @PutMapping(path = "/{id}/friends/{friendId}")
    public void addFriend(@PathVariable Long id, @PathVariable Long friendId) {
        userService.checkUserExists(id);
        userService.checkUserExists(friendId);

        User user = userService.getUser(id);
        User friend = userService.getUser(friendId);

        userService.addToFriends(user, friend);
    }

    @DeleteMapping(path = "/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable Long id, @PathVariable Long friendId) {
        userService.checkUserExists(id);
        userService.checkUserExists(friendId);

        User user = userService.getUser(id);
        User friend = userService.getUser(friendId);

        userService.removeFromFriends(user, friend);
    }

    @GetMapping(path = "/{id}/friends/common/{otherId}")
    public List<User> getFriends(@PathVariable Long id, @PathVariable Long otherId) {
        User user = userService.getUser(id);
        User friend = userService.getUser(otherId);

        return userService.getCommonFriends(user, friend);
    }
}
