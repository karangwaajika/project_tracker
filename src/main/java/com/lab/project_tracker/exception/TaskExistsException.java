package com.lab.project_tracker.exception;

public class TaskExistsException extends AppException{
    public TaskExistsException(String message) {
        super(message);
    }
}
