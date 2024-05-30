package com.project.usersystem.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends UserSystemException {

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
