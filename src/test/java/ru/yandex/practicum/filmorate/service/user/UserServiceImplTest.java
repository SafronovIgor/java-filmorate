package ru.yandex.practicum.filmorate.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserStorage userStorage;
    UserService userService;

    @BeforeEach
    public void setUp() {
        userStorage = new InMemoryUserStorage();
        userService = new UserServiceImpl(userStorage);

    }

    @Test
    void getUsers() {
        User user = new User();
        userStorage.setNewId(user);
        userStorage.addUser(user);

        User user2 = new User();
        userStorage.setNewId(user2);
        userStorage.addUser(user2);

        Collection<User> users = userService.getUsers();

        assertFalse(users.isEmpty());
        assertEquals(2, users.size());
    }

    @Test
    void getUser() {
        User user = new User();
        userStorage.setNewId(user);
        userStorage.addUser(user);
        User userById = userService.getUser(user.getId());
        assertNotNull(userById);
    }

    @Test
    void createUser() {
        User user = userService.createUser(new User());
        assertNotNull(user);
    }

    @Test
    void updateUser() {
        User user = new User();
        userStorage.setNewId(user);
        userStorage.addUser(user);
        user.setName("test update");
        userService.updateUser(user);
        assertEquals("test update", user.getName());
    }
}