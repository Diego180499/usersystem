package com.project.usersystem.exception;

import org.springframework.http.HttpStatus;

public class UserSystemException  extends  RuntimeException{

    private HttpStatus httpStatus;

    public UserSystemException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public UserSystemException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public UserSystemException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public UserSystemException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public UserSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
