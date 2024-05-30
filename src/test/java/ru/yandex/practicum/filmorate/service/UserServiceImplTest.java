//package ru.yandex.practicum.filmorate.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
//import ru.yandex.practicum.filmorate.model.User;
//import ru.yandex.practicum.filmorate.service.user.UserServiceImpl;
//import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
//
//import java.util.Collection;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserServiceImplTest {
//
//    private UserServiceImpl userServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//        userServiceImpl = new UserServiceImpl(new InMemoryUserStorage());
//    }
//
//    @Test
//    void testGetUsers() {
//        Collection<User> users = userServiceImpl.getUsers();
//
//        assertNotNull(users);
//        assertTrue(users.isEmpty());
//    }
//
//    @Test
//    void testAssignNewId() {
//        var user = new User();
//        userServiceImpl.assignNewId(user);
//        assertNotEquals(0, user.getId());
//    }
//
//    @Test
//    void testAddUserToMap() {
//        var user = new User();
//        user.setId(1L);
//        user.setLogin("testuser");
//
//        userServiceImpl.addUserToMap(user);
//
//        Collection<User> usersCollection = userServiceImpl.getUsers();
//        assertTrue(usersCollection.contains(user));
//    }
//
//    @Test
//    void testCheckUserExists() {
//        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.checkUserExists(1L));
//
//        var user = new User();
//        user.setId(1L);
//        userServiceImpl.addUserToMap(user);
//
//        assertDoesNotThrow(() -> userServiceImpl.checkUserExists(1L));
//    }
//
//    @Test
//    void testUpdateNameFromLoginIfEmpty() {
//        var user = new User();
//        user.setLogin("testuser");
//
//        userServiceImpl.updateNameFromLoginIfEmpty(user);
//
//        assertEquals("testuser", user.getName());
//
//
//        var userWithExistingName = new User();
//        userWithExistingName.setLogin("testuser");
//        userWithExistingName.setName("existingname");
//
//        userServiceImpl.updateNameFromLoginIfEmpty(userWithExistingName);
//
//        assertEquals("existingname", userWithExistingName.getName());
//    }
//
//    @Test
//    void testAddToFriends() {
//        var user1 = new User();
//        var user2 = new User();
//        userServiceImpl.assignNewId(user1);
//        userServiceImpl.assignNewId(user2);
//        userServiceImpl.addToFriends(user1, user2);
//
//        assertTrue(user1.getFriends().contains(user2.getId()));
//        assertTrue(user2.getFriends().contains(user1.getId()));
//    }
//
//    @Test
//    void testRemoveFromFriends_Success() {
//        var user1 = new User();
//        var user2 = new User();
//        userServiceImpl.assignNewId(user1);
//        userServiceImpl.assignNewId(user2);
//        userServiceImpl.addToFriends(user1, user2);
//
//        userServiceImpl.removeFromFriends(user1, user2);
//
//        assertFalse(user1.getFriends().contains(user2.getId()));
//        assertFalse(user2.getFriends().contains(user1.getId()));
//    }
//
//    @Test
//    void testRemoveFromFriends_Empty_Sets() {
//        var user1 = new User();
//        var user2 = new User();
//        userServiceImpl.assignNewId(user1);
//        userServiceImpl.assignNewId(user2);
//
//        userServiceImpl.removeFromFriends(user1, user2);
//
//        assertFalse(user1.getFriends().contains(user2.getId()));
//        assertFalse(user2.getFriends().contains(user1.getId()));
//    }
//
//    @Test
//    void testGetCommonFriends_NoCommonFriends() {
//        var user1 = new User();
//        user1.setId(1L);
//        userServiceImpl.addUserToMap(user1);
//
//        var user2 = new User();
//        user2.setId(2L);
//        userServiceImpl.addUserToMap(user2);
//
//        List<User> commonFriends = userServiceImpl.getCommonFriends(user1, user2);
//
//        assertTrue(commonFriends.isEmpty());
//    }
//
//    @Test
//    void testGetCommonFriends_CommonsFriendsExist() {
//        var user1 = new User();
//        user1.setId(1L);
//        userServiceImpl.addUserToMap(user1);
//
//        var user2 = new User();
//        user2.setId(2L);
//        userServiceImpl.addUserToMap(user2);
//
//        var commonFriend = new User();
//        commonFriend.setId(3L);
//        userServiceImpl.addUserToMap(commonFriend);
//
//        userServiceImpl.addToFriends(user1, commonFriend);
//        userServiceImpl.addToFriends(user2, commonFriend);
//
//        List<User> commonFriends = userServiceImpl.getCommonFriends(user1, user2);
//
//        assertFalse(commonFriends.isEmpty());
//        assertTrue(commonFriends.contains(commonFriend));
//    }
//
//    @Test
//    void testGetCommonFriends_UserDoesNotExist() {
//        var user1 = new User();
//        user1.setId(1L);
//        userServiceImpl.addUserToMap(user1);
//
//        var user2 = new User();
//        user2.setId(2L);
//
//        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getCommonFriends(user1, user2));
//    }
//}
