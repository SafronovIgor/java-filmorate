package ru.yandex.practicum.filmorate.service.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final UserService userService;
    private final UserStorage userStorage;

    @Override
    public List<User> getFriends(long id) {
        userStorage.userExists(id);
        return userStorage.getFriends(id);
    }

    @Override
    public void addFriend(long userId, long friendId) {
        User user = userService.getUser(userId);
        User friend = userService.getUser(friendId);

        Set<Long> sourceFriends = user.getFriends();
        sourceFriends.add(friend.getId());

        Set<Long> targetFriends = friend.getFriends();
        targetFriends.add(user.getId());
    }

    @Override
    public void deleteFriend(long userId, long friendId) {
        User user = userService.getUser(userId);
        User friend = userService.getUser(friendId);

        Set<Long> sourceFriends = user.getFriends();
        Set<Long> targetFriends = friend.getFriends();
        if (sourceFriends != null && !sourceFriends.isEmpty()) {
            sourceFriends.remove(friend.getId());
        }
        if (targetFriends != null && !targetFriends.isEmpty()) {
            targetFriends.remove(user.getId());
        }
    }
}
