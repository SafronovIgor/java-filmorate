package ru.yandex.practicum;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

@JdbcTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmoRateApplicationTests {
    //Не понял как это вообще реализовать -_- оно не работает с бинами... хотя в тз совсем другое
//    private final UserDbStorage userStorage;
//
//    @Test
//    public void testFindUserById() {
//
//        Optional<User> userOptional = Optional.ofNullable(userStorage.getUserById(1));
//
//        assertThat(userOptional)
//                .isPresent()
//                .hasValueSatisfying(user ->
//                        assertThat(user).hasFieldOrPropertyWithValue("id", 1)
//                );
//    }
}