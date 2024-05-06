package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void testValidationUser() {
        // Тестирование случая, когда User null
        assertThrows(ValidationException.class, () -> UserController.validationUser(null));

        // Тестирование случая, когда Email пустой
        User userEmptyEmail = new User();
        userEmptyEmail.setEmail("");
        assertThrows(ValidationException.class, () -> UserController.validationUser(userEmptyEmail));

        // Тестирование случая, когда Email не содержит @
        User userInvalidEmail = new User();
        userInvalidEmail.setEmail("invalidemail.com");
        assertThrows(ValidationException.class, () -> UserController.validationUser(userInvalidEmail));

        // Тестирование случая, когда Login содержит пробелы
        User userInvalidLogin = new User();
        userInvalidLogin.setLogin("invalid login");
        assertThrows(ValidationException.class, () -> UserController.validationUser(userInvalidLogin));

        // Тестирование случая, когда Birthday в будущем
        User userFutureBirthday = new User();
        userFutureBirthday.setBirthday(LocalDate.now().plusDays(1));
        assertThrows(ValidationException.class, () -> UserController.validationUser(userFutureBirthday));
    }
}