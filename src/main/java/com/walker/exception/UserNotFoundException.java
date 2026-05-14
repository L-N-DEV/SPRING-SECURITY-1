package com.walker.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;

    public UserNotFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public UserNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public UserNotFoundException(String fieldName, String fieldValue) {
        super("User not found with " + fieldName + ": " + fieldValue);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}