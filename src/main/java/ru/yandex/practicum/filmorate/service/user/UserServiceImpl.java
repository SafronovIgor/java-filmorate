package ru.yandex.practicum.filmorate.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserServiceImpl(@Qualifier("inMemoryUserStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public Collection<User> getUsers() {
        return userStorage.getUsers();
    }

    @Override
    public User getUser(long id) {
        userStorage.userExists(id);
        return userStorage.getUserById(id);
    }

    @Override
    public User createUser(User user) {
        userStorage.setNewId(user);
        userStorage.updateEmptyNameFromLogin(user);
        userStorage.addUser(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        userStorage.userExists(user.getId());
        userStorage.addUser(user);
        return user;
    }
}
