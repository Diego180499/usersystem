package com.project.usersystem.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(UserSystemException.class)
    public ResponseEntity<ErrorDTO> handleUserSystemException(UserSystemException userSystemException) {
        log.warn("Flow finished by exception: {}", userSystemException.getMessage());
        return ResponseEntity.status(userSystemException.getHttpStatus())
                .body(new ErrorDTO(userSystemException.getMessage()));
    }
}
