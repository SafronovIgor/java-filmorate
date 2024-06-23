package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {

    User getUserById(long id);

    Collection<User> getUsers();

    User addUser(User obj);

    User updateUser(User obj);

    boolean userExists(long id);
}
