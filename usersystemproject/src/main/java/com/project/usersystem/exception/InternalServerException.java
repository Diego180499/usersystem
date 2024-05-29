package com.project.usersystem.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends UserSystemException{

    public InternalServerException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
