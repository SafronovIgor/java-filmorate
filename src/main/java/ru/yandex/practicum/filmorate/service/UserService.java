package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();

    public Collection<User> getUsers() {
        return users.values();
    }

    public void assignNewId(User obj) {
        obj.setId(ServiceUtil.generateNewId(users));
    }

    public void addUserToMap(User user) {
        users.put(user.getId(), user);
    }

    public void checkUserExists(Long userId) {
        if (!users.containsKey(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }

    public void updateNameFromLoginIfEmpty(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
    }
}
