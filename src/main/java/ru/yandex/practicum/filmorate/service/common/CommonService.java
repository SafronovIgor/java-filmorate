package ru.yandex.practicum.filmorate.service.common;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface CommonService {

    List<User> getCommonFriends(long id, long otherId);
}
