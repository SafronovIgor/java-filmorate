package ru.yandex.practicum.filmorate.storage.user;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.util.ServiceUtil;

import java.util.*;

@Getter
@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Long, User> usersInMemory = new HashMap<>();

    @Override
    public User getUserById(long id) {
        return usersInMemory.get(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(usersInMemory.values());
    }

    @Override
    public void setNewId(User obj) {
        obj.setId(ServiceUtil.generateNewId(usersInMemory));
    }

    @Override
    public void addUser(User obj) {
        usersInMemory.put(obj.getId(), obj);
    }

    @Override
    public boolean userExists(long id) {
        return usersInMemory.containsKey(id);
    }

    @Override
    public boolean friendExists(User user, long id) {
        return user.getFriends().contains(id);
    }

    @Override
    public void updateEmptyNameFromLogin(User obj) {
        if (obj.getName() == null || obj.getName().isBlank()) {
            obj.setName(obj.getLogin());
        }
    }
}
