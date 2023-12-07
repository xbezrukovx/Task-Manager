package com.example.TaskManager.dto.tasks.request;

import com.example.TaskManager.enums.TaskPriority;
import com.example.TaskManager.enums.TaskStatus;
import com.example.TaskManager.models.User;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskCreateDTO {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private UUID responsibleUserId;
}
