package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.friend.FriendService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/friends")
@RequiredArgsConstructor
public class FriendsController {
    private final FriendService friendService;

    @GetMapping(produces = "application/json")
    public List<User> getFriends(@PathVariable Long userId) {
        log.info("Fetching friends for user with id: {}", userId);
        return friendService.getFriends(userId);
    }

    @PutMapping(path = "/{friendId}")
    public void addFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        log.info("Adding friend with id: {} to user with id: {}", friendId, userId);
        friendService.addFriend(userId, friendId);
    }

    @DeleteMapping(path = "/{friendId}")
    public void deleteFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        log.info("Deleting friend with id: {} from user with id: {}", friendId, userId);
        friendService.deleteFriend(userId, friendId);
    }
}
