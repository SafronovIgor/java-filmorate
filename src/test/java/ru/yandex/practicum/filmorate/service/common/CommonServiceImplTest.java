package ru.yandex.practicum.filmorate.service.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.friend.FriendService;
import ru.yandex.practicum.filmorate.service.friend.FriendServiceImpl;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.service.user.UserServiceImpl;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

class CommonServiceImplTest {
    FriendService friendService;
    UserService userService;
    UserStorage userStorage;
    CommonService commonService;

    @BeforeEach
    public void setUp() {
        userStorage = new InMemoryUserStorage();
        commonService = new CommonServiceImpl(userStorage);
        userService = new UserServiceImpl(userStorage);
        friendService = new FriendServiceImpl(userService, userStorage);
    }

    @Test
    void getCommonFriends() {
        User user = new User();
        userStorage.setNewId(user);
        userStorage.addUser(user);

        User user2 = new User();
        userStorage.setNewId(user2);
        userStorage.addUser(user2);

        User user3 = new User();
        userStorage.setNewId(user3);
        userStorage.addUser(user3);

        friendService.addFriend(user.getId(), user3.getId());
        friendService.addFriend(user2.getId(), user3.getId());

        List<User> commonFriends = commonService.getCommonFriends(user.getId(), user2.getId());
        System.out.println(commonFriends);
    }

}