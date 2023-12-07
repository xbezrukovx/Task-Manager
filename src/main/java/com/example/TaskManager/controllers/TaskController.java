package com.example.TaskManager.controllers;

import com.example.TaskManager.dto.tasks.request.TaskCreateDTO;
import com.example.TaskManager.dto.tasks.request.TaskUpdateDTO;
import com.example.TaskManager.dto.tasks.response.TaskShortDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Task controller", description = "Task manager")
public interface TaskController {
    @Operation(
            summary = "Getting a list of tasks",
            description = "Getting a list of tasks that current user has made."
    )
    @GetMapping("/")
    List<TaskShortDTO> getTasks();
    @Operation(
            summary = "Getting a list of tasks by author",
            description = "Getting a list of tasks that provided author has made."
    )
    @GetMapping("/?author={id}")
    List<TaskShortDTO> getTasksByAuthorId(UUID id);
    @Operation(
            summary = "Getting the task by its identifier",
            description = "Getting the task by its identifier."
    )
    @GetMapping("/{id}")
    TaskShortDTO getTask(UUID id);
    @Operation(
            summary = "Creating a new task",
            description = "Creating a new task."
    )
    @PostMapping("/")
    void createTask(TaskCreateDTO taskData);

    @Operation(
            summary = "Updating a task info",
            description = "Updating an information about provided task."
    )
    @PatchMapping("/{id}")
    void updateTask(TaskUpdateDTO taskData, UUID taskId);

    @Operation(
            summary = "Setting a user as responsible",
            description = "Setting a user as responsible for given task."
    )
    @PutMapping("/{id}")
    void setResponsible(UUID taskId, UUID responsibleUserId);
}
