package com.project.usersystem.exception;

import org.springframework.http.HttpStatus;

public class UnauthenticatedException extends UserSystemException {

    public UnauthenticatedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
