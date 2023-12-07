package com.example.TaskManager.exceptions;

public class TaskNotFoundException extends TaskServiceGeneralException{
    public TaskNotFoundException(String message) {
        super(message);
    }
}
