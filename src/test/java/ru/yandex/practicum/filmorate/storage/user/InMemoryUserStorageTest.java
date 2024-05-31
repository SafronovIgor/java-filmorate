package ru.yandex.practicum.filmorate.storage.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserStorageTest {
    UserStorage userStorage;

    @BeforeEach
    void setUp() {
        userStorage = new InMemoryUserStorage();
        IntStream.range(0, 10).forEach(i -> {
            User user = new User();
            userStorage.setNewId(user);
            userStorage.addUser(user);
        });
    }

    @Test
    void getUserById() {
        User expectedUser = new User();
        userStorage.addUser(expectedUser);
        User actualUser = userStorage.getUserById(expectedUser.getId());
        assertEquals(expectedUser, actualUser, "The retrieved user is not as expected.");
    }

    @Test
    void getUsers() {
        assertEquals(10, userStorage.getUsers().size(), "There should be 10 users.");
    }

    @Test
    void setNewId() {
        User userById = userStorage.getUserById(1);
        userStorage.setNewId(userById);
        assertNotEquals(0, userById.getId(), "The user id is not as expected.");
    }

    @Test
    void addUser() {
        int size = userStorage.getUsers().size();
        userStorage.addUser(new User());
        int size2 = userStorage.getUsers().size();
        assertNotEquals(size, size2, "The user id is not as expected.");
    }

    @Test
    void userExists() {
        assertThrowsExactly(
                ResourceNotFoundException.class,
                () -> userStorage.userExists(999),
                "Expected userExists() to throw an ResourceNotFoundException, but it didn't"
        );
    }

    @Test
    void updateEmptyNameFromLogin() {
        User user = new User();
        user.setLogin("qwerty");
        userStorage.setNewId(user);
        userStorage.addUser(user);
        userStorage.updateEmptyNameFromLogin(user);
        assertEquals(user.getName(), user.getLogin());
    }

    @Test
    void getFriends() {
        User user = new User();
        userStorage.setNewId(user);
        userStorage.addUser(user);
        List<User> friends = userStorage.getFriends(user.getId());
        user.getFriends().add(5L);
        List<User> friends2 = userStorage.getFriends(user.getId());

        assertTrue(friends.isEmpty());
        assertFalse(friends2.isEmpty());

    }
}