package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.common.CommonService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/friends/common")
@RequiredArgsConstructor
public class CommonController {
    private final CommonService commonService;

    @GetMapping(path = "/{otherId}")
    public List<User> getFriends(@PathVariable Long userId, @PathVariable Long otherId) {
        log.info("Fetching common friends for users with ids: {} and {}", userId, otherId);
        return commonService.getCommonFriends(userId, otherId);
    }
}
