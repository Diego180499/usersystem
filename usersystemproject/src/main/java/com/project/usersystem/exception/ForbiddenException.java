package com.project.usersystem.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends UserSystemException {

    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
