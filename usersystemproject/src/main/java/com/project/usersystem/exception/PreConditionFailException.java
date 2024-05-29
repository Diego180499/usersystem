package com.project.usersystem.exception;

import org.springframework.http.HttpStatus;

public class PreConditionFailException extends  UserSystemException{

    public PreConditionFailException(String message) {
        super(message, HttpStatus.PRECONDITION_FAILED);
    }
}
