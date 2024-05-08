package ru.yandex.practicum.filmorate.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yandex.practicum.filmorate.model.HttpError;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<HttpError> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.error("ValidationException: {}", e.getMessage());
        String errorMessage = e.getBindingResult().getFieldError() != null
                ? e.getBindingResult().getFieldError().getDefaultMessage()
                : "Unknown error";
        HttpError httpError = new HttpError(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(httpError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<HttpError> catchResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage(), e);
        HttpError httpError = new HttpError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(httpError, HttpStatus.NOT_FOUND);
    }
}
