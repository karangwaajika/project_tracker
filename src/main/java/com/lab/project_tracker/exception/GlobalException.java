package com.lab.project_tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ProjectExistsException.class)
    public ResponseEntity<?> handleProjectExists(ProjectExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("Error", e.getMessage()));
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<?> handleProjectNotFound(ProjectNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("Error", e.getMessage()));
    }
}
