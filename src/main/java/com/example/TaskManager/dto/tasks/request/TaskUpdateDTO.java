package com.example.TaskManager.dto.tasks.request;

import com.example.TaskManager.enums.TaskPriority;
import com.example.TaskManager.enums.TaskStatus;
import lombok.Data;

@Data
public class TaskUpdateDTO {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
}
