package com.lab.project_tracker.exception;

public class TaskNotFoundException extends AppException{
    public TaskNotFoundException(String message) {
        super(message);
    }
}
