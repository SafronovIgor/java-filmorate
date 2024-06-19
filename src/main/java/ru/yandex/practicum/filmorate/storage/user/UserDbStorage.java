package ru.yandex.practicum.filmorate.storage.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.UserRepository;

import java.util.Collection;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDbStorage implements UserStorage {
    private final UserRepository userRepository;

    @Override
    public User getUserById(long id) {
        userExists(id);
        return userRepository.findById(id);
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public User updateUser(User obj) {
        userExists(obj.getId());
        userRepository.update(obj);
        return userRepository.findById(obj.getId());
    }

    @Override
    public boolean userExists(long id) {
        var existsById = userRepository.existsById(id);

        if (!existsById) {
            log.warn("User with ID: {} not found.", id);
            throw new ResourceNotFoundException("User with ID: " + id + " not found.");
        }
        return true;
    }
}
