package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserServiceImpl {

    Collection<User> getUsers();

    void assignNewId(User obj);

    void addUserToMap(User obj);

    void checkUserExists(long id);

    void updateNameFromLoginIfEmpty(User obj);
}
