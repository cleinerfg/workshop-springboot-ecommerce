package org.workshop.ecommerce.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(
            ResourceNotFoundException e,
            HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError StError = StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(error)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(StError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(
            DatabaseException e,
            HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError StError = StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(error)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(StError);
    }
}
