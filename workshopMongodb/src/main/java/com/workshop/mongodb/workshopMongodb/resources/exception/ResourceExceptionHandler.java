package com.workshop.mongodb.workshopMongodb.resources.exception;

import com.workshop.mongodb.workshopMongodb.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(status)
                .body(new StandardError(
                        System.currentTimeMillis(),
                        status.value(),
                        "Não encontrado",
                        e.getMessage(),
                        request.getRequestURI()));
    }
}
