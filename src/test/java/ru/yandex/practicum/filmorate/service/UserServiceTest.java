package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testGetUsers() {
        Collection<User> users = userService.getUsers();
        assertNotNull(users);
        assertTrue(users.isEmpty());
    }

    @Test
    void testAssignNewId() {
        var user = new User();
        userService.assignNewId(user);
        assertNotEquals(0, user.getId());
    }

    @Test
    void testAddUserToMap() {
        var user = new User();
        user.setId(1L);
        user.setLogin("testuser");

        userService.addUserToMap(user);

        Collection<User> usersCollection = userService.getUsers();
        assertTrue(usersCollection.contains(user));
    }

    @Test
    void testCheckUserExists() {
        assertThrows(ResourceNotFoundException.class, () -> userService.checkUserExists(1L));

        var user = new User();
        user.setId(1L);
        userService.addUserToMap(user);

        assertDoesNotThrow(() -> userService.checkUserExists(1L));
    }

    @Test
    void testUpdateNameFromLoginIfEmpty() {
        var user = new User();
        user.setLogin("testuser");

        userService.updateNameFromLoginIfEmpty(user);

        assertEquals("testuser", user.getName());


        var userWithExistingName = new User();
        userWithExistingName.setLogin("testuser");
        userWithExistingName.setName("existingname");

        userService.updateNameFromLoginIfEmpty(userWithExistingName);

        assertEquals("existingname", userWithExistingName.getName());
    }
}
