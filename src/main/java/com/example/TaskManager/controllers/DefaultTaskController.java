package com.example.TaskManager.controllers;

import com.example.TaskManager.dto.tasks.request.TaskCreateDTO;
import com.example.TaskManager.dto.tasks.request.TaskUpdateDTO;
import com.example.TaskManager.dto.tasks.response.TaskShortDTO;
import com.example.TaskManager.services.TaskService;
import com.example.TaskManager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class DefaultTaskController implements TaskController{
    private final TaskService taskService;
    private final UserService userService;

    @Override
    public TaskShortDTO getTask(@PathVariable UUID id) {
        return taskService.getTask(id);
    }

    @Override
    public List<TaskShortDTO> getTasks() {
        UUID userId = userService.getCurrentUserId();
        return taskService.getTasksByUserId(userId);
    }

    @Override
    public List<TaskShortDTO> getTasksByAuthorId(@RequestBody UUID authorId) {
        return taskService.getTasksByUserId(authorId);
    }

    @Override
    public void createTask(@RequestBody TaskCreateDTO taskData) {
        UUID userId = userService.getCurrentUserId();
        taskService.createTask(taskData, userId);
    }

    @Override
    public void updateTask(@RequestBody TaskUpdateDTO taskData, @PathVariable UUID taskId) {
        taskService.modifyTask(taskData, taskId);
    }

    @Override
    public void setResponsible(@PathVariable UUID taskId, @PathVariable UUID responsibleUserId) {
        taskService.setResponsible(taskId, responsibleUserId);
    }
}
