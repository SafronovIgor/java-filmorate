package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.List;

public interface UserStorage {

    User getUserById(long id);

    Collection<User> getUsers();

    void setNewId(User obj);

    void addUser(User obj);

    boolean userExists(long id);

    void updateEmptyNameFromLogin(User obj);

    List<User> getFriends(long id);
}