package ru.yandex.practicum.filmorate.service.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceImpl {
    private final UserStorage userStorage;

    public UserService(@Qualifier("inMemoryUserStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Collection<User> getUsers() {
        return userStorage.getUsers();
    }

    public User getUser(long id) {
        return userStorage.getUserById(id);
    }

    public void assignNewId(User obj) {
        userStorage.setNewId(obj);
    }

    public void addUserToMap(User user) {
        userStorage.addUser(user);
    }

    public void checkUserExists(long id) {
        if (!userStorage.userExists(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    public void checkFriendsExist(User user, long id) {
        if (!userStorage.friendExists(user, id)) {
            throw new ResourceNotFoundException("Friend not found with id: " + id);
        }
    }

    public void updateNameFromLoginIfEmpty(User user) {
        userStorage.updateEmptyNameFromLogin(user);
    }

    @Override
    public void addToFriends(User source, User target) {
        Set<Long> sourceFriends = source.getFriends();
        sourceFriends.add(target.getId());

        Set<Long> targetFriends = target.getFriends();
        targetFriends.add(source.getId());
    }

    @Override
    public void removeFromFriends(User source, User target) {
        Set<Long> sourceFriends = source.getFriends();
        Set<Long> targetFriends = target.getFriends();
        if (sourceFriends != null && !sourceFriends.isEmpty()) {
            sourceFriends.remove(target.getId());
        }
        if (targetFriends != null && !targetFriends.isEmpty()) {
            targetFriends.remove(source.getId());
        }
    }

    @Override
    public List<User> getCommonFriends(User obj, User obj2) {
        checkUserExists(obj.getId());
        checkUserExists(obj2.getId());

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
