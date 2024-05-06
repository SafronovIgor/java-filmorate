package ru.yandex.practicum.filmorate.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationException extends RuntimeException {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public ValidationException(String message) {
        super(message);
    }

    public <T> ValidationException(String message, T validationClass) {
        super(message);
        log.error("{}   * Data: {}", ANSI_RED + message + ANSI_RESET, validationClass);
    }
}
