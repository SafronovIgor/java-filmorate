package ru.yandex.practicum.filmorate.service.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final UserStorage userStorage;

    @Override
    public List<User> getCommonFriends(long userId, long otherId) {
        userStorage.userExists(userId);
        userStorage.userExists(otherId);

        User obj = userStorage.getUserById(userId);
        User obj2 = userStorage.getUserById(otherId);

        User objById = userStorage.getUserById(obj.getId());
        List<User> objCommonFriends = objById.getFriends().stream()
                .map(userStorage::getUserById)
                .collect(Collectors.toList());

        User obj2ById = userStorage.getUserById(obj2.getId());
        List<User> obj2CommonFriends = obj2ById.getFriends().stream()
                .map(userStorage::getUserById)
                .collect(Collectors.toList());

        objCommonFriends.retainAll(obj2CommonFriends);

        return objCommonFriends;
    }
}
