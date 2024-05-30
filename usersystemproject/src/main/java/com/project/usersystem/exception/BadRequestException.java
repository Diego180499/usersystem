package com.project.usersystem.exception;


import org.springframework.http.HttpStatus;

public class BadRequestException extends UserSystemException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
