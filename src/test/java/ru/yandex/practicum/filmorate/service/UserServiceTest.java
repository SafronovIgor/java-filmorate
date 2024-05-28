package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(new InMemoryUserStorage());
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

    @Test
    void testAddToFriends() {
        var user1 = new User();
        var user2 = new User();
        userService.assignNewId(user1);
        userService.assignNewId(user2);
        userService.addToFriends(user1, user2);

        assertTrue(user1.getFriends().contains(user2.getId()));
        assertTrue(user2.getFriends().contains(user1.getId()));
    }

    @Test
    void testRemoveFromFriends_Success() {
        var user1 = new User();
        var user2 = new User();
        userService.assignNewId(user1);
        userService.assignNewId(user2);
        userService.addToFriends(user1, user2);

        userService.removeFromFriends(user1, user2);

        assertFalse(user1.getFriends().contains(user2.getId()));
        assertFalse(user2.getFriends().contains(user1.getId()));
    }

    @Test
    void testRemoveFromFriends_Empty_Sets() {
        var user1 = new User();
        var user2 = new User();
        userService.assignNewId(user1);
        userService.assignNewId(user2);

        userService.removeFromFriends(user1, user2);

        assertFalse(user1.getFriends().contains(user2.getId()));
        assertFalse(user2.getFriends().contains(user1.getId()));
    }

    @Test
    void testGetCommonFriends_NoCommonFriends() {
        var user1 = new User();
        user1.setId(1L);
        userService.addUserToMap(user1);

        var user2 = new User();
        user2.setId(2L);
        userService.addUserToMap(user2);

        List<User> commonFriends = userService.getCommonFriends(user1, user2);

        assertTrue(commonFriends.isEmpty());
    }

    @Test
    void testGetCommonFriends_CommonsFriendsExist() {
        var user1 = new User();
        user1.setId(1L);
        userService.addUserToMap(user1);

        var user2 = new User();
        user2.setId(2L);
        userService.addUserToMap(user2);

        var commonFriend = new User();
        commonFriend.setId(3L);
        userService.addUserToMap(commonFriend);

        userService.addToFriends(user1, commonFriend);
        userService.addToFriends(user2, commonFriend);

        List<User> commonFriends = userService.getCommonFriends(user1, user2);

        assertFalse(commonFriends.isEmpty());
        assertTrue(commonFriends.contains(commonFriend));
    }

    @Test
    void testGetCommonFriends_UserDoesNotExist() {
        var user1 = new User();
        user1.setId(1L);
        userService.addUserToMap(user1);

        var user2 = new User();
        user2.setId(2L);

        assertThrows(ResourceNotFoundException.class, () -> userService.getCommonFriends(user1, user2));
    }
}
