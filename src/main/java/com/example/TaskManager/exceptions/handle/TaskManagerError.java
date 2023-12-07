package com.example.TaskManager.exceptions.handle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskManagerError {
    private int statusCode;
    private String message;
}
