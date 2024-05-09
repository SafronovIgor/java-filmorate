package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserServiceImpl {
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public Collection<User> getUsers() {
        return users.values();
    }

    @Override
    public void assignNewId(User obj) {
        obj.setId(ServiceUtil.generateNewId(users));
    }

    @Override
    public void addUserToMap(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void checkUserExists(long userId) {
        if (!users.containsKey(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }

    @Override
    public void updateNameFromLoginIfEmpty(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }
}
