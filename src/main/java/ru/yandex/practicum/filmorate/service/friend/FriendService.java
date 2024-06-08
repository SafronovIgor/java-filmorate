package ru.yandex.practicum.filmorate.service.friend;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface FriendService {

    List<User> getFriends(long id);

    void addFriend(long userId, long friendId);

    void deleteFriend(long userId, long friendId);

}
