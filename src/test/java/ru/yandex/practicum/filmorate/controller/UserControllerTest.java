package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    public void testValidationUser() {
        // Тестирование случая, когда User null
        var exceptionNullUser = assertThrows(ValidationException.class, () -> UserController.validationUser(null));
        assertEquals("User object is null.", exceptionNullUser.getMessage());

        // Тестирование случая, когда Email пустой
        User userEmptyEmail = new User();
        var exceptionEmptyEmail = assertThrows(ValidationException.class,
                () -> UserController.validationUser(userEmptyEmail));
        assertTrue(exceptionEmptyEmail.getMessage().contains("Email cannot be empty or null."));

        // Тестирование случая, когда Email не содержит @
        User userInvalidEmail = new User();
        userInvalidEmail.setEmail("invalidemail.com");
        var exceptionInvalidEmail = assertThrows(ValidationException.class,
                () -> UserController.validationUser(userInvalidEmail));
        assertTrue(exceptionInvalidEmail.getMessage().contains("Email must contain the @ symbol."));

        // Тестирование случая, когда Login пустой
        User userEmptyLogin = new User();
        var exceptionEmptyLogin = assertThrows(ValidationException.class,
                () -> UserController.validationUser(userEmptyLogin));
        assertTrue(exceptionEmptyLogin.getMessage().contains("Login cannot be empty or null."));

        // Тестирование случая, когда Login содержит пробел
        User userInvalidLogin = new User();
        userInvalidLogin.setLogin("invalid login");
        var exceptionInvalidLogin = assertThrows(ValidationException.class,
                () -> UserController.validationUser(userInvalidLogin));
        assertTrue(exceptionInvalidLogin.getMessage().contains("Login cannot contain spaces."));

        // Тестирование случая, когда Name пустое
        User userEmptyName = new User();
        var exceptionEmptyName = assertThrows(ValidationException.class,
                () -> UserController.validationUser(userEmptyName));
        assertTrue(exceptionEmptyName.getMessage().contains("Name cannot be empty or null."));

        // Тестирование случая, когда Date of birth null
        User userNullBirthday = new User();
        var exceptionNullBirthday = assertThrows(ValidationException.class,
                () -> UserController.validationUser(userNullBirthday));
        assertTrue(exceptionNullBirthday.getMessage().contains("Date of birth cannot be null."));

        // Тестирование случая, когда Date of birth в будущем
        User userFutureBirthday = new User();
        userFutureBirthday.setBirthday(LocalDate.now().plusMonths(1));
        var exceptionFutureBirthday = assertThrows(ValidationException.class,
                () -> UserController.validationUser(userFutureBirthday));
        assertTrue(exceptionFutureBirthday.getMessage().contains("Date of birth cannot be in the future."));

        // Тестирование случая, когда Email, Login, Name и Birthday передаются как "null" в виде строки
        User userNullStrings = new User();
        userNullStrings.setEmail("null");
        userNullStrings.setLogin("null");
        userNullStrings.setName("null");
        userNullStrings.setBirthday(null);
        var exceptionNullStrings = assertThrows(ValidationException.class,
                () -> UserController.validationUser(userNullStrings));
        assertTrue(exceptionNullStrings.getMessage().contains("The field email cannot contain the string value null."));
        assertTrue(exceptionNullStrings.getMessage().contains("The field login cannot contain the string value null."));
        assertTrue(exceptionNullStrings.getMessage().contains("The field name cannot contain the string value null."));
        assertTrue(exceptionNullStrings.getMessage().contains("Date of birth cannot be null."));
    }
}