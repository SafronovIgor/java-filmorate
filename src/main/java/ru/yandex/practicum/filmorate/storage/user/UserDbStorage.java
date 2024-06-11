package ru.yandex.practicum.filmorate.storage.user;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Primary
@Component
@RequiredArgsConstructor
public class UserDbStorage implements UserStorage {
    private final UserRepository userRepository;

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User obj) {
        userRepository.save(obj);
    }

    @Override
    public void updateUser(User obj) {
        userRepository.update(obj);
    }

    @Override
    public boolean userExists(long id) {
        return userRepository.existsById(id);
    }

    @Override
    public void updateEmptyNameFromLogin(User obj) {
        User user = userRepository.findById(obj.getId());
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(obj.getLogin());
        }
        userRepository.update(user);
    }

    @Override
    public List<User> getFriends(long id) {
        return List.of();
    }
}
