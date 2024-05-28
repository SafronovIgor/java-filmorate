package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserServiceImpl {

    void addToFriends(User source, User target);

    void removeFromFriends(User sender, User receiver);

    List<User> getCommonFriends(User obj1, User obj2);
}
