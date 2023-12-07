package com.example.TaskManager.services.implementation;

import com.example.TaskManager.dto.tasks.request.TaskCreateDTO;
import com.example.TaskManager.dto.tasks.request.TaskUpdateDTO;
import com.example.TaskManager.dto.tasks.response.TaskShortDTO;
import com.example.TaskManager.exceptions.TaskNotFoundException;
import com.example.TaskManager.exceptions.TaskServiceGeneralException;
import com.example.TaskManager.models.Task;
import com.example.TaskManager.models.User;
import com.example.TaskManager.repositories.TaskRepository;
import com.example.TaskManager.services.TaskService;
import com.example.TaskManager.services.UserService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultTaskService implements TaskService {
    private final UserService userService;
    private final TaskRepository taskRepository;
    private final ObjectMapper objectMapper;

    @Override
    public TaskShortDTO setResponsible(UUID taskId, UUID responsibleUserId) {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskServiceGeneralException("No such task"));
        User responsibleUser = userService.getUserById(responsibleUserId);
        UUID currentUserId = userService.getCurrentUserId();
        UUID existingTaskUserId = existingTask.getAuthor().getId();
        if (!currentUserId.equals(existingTaskUserId)) {
            throw new TaskServiceGeneralException("Forbidden");
        }

        existingTask.setResponsible(responsibleUser);
        existingTask = taskRepository.save(existingTask);
        return objectMapper.convertValue(existingTask, TaskShortDTO.class);
    }

    public TaskShortDTO getTask(UUID taskId) {
        return objectMapper.convertValue(
                getTaskFromDB(taskId),
                TaskShortDTO.class
        );
    }

    @Override
    public void createTask(TaskCreateDTO taskData, UUID userId) {
        log.info(MessageFormat.format("A new task is creating by user: {0} ",userId));
        User responsibleUser = null;
        if (taskData.getResponsibleUserId() != null) {
            responsibleUser = userService.getUserById(taskData.getResponsibleUserId());
        }

        Task task = objectMapper.convertValue(taskData, Task.class);
        User user = userService.getUserById(userId);
        task.setAuthor(user);
        task.setResponsible(responsibleUser);
        taskRepository.save(task);
    }

    @Override
    public List<TaskShortDTO> getTasksByUserId(UUID userId) {
        log.info(MessageFormat.format("Getting tasks by Author: {0}. Request from user: {1}", userId, userService.getCurrentUserId()));
        return taskRepository
                .findByAuthor(userId)
                .stream()
                .map(t -> objectMapper.convertValue(t, TaskShortDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskShortDTO modifyTask(TaskUpdateDTO taskData, UUID taskId) {
        Task existingTask = getTaskFromDB(taskId);
        UUID currentUserId = userService.getCurrentUserId();
        UUID existingTaskUserId = existingTask.getAuthor().getId();
        if (!currentUserId.equals(existingTaskUserId)) {
            throw new TaskServiceGeneralException("Forbidden");
        }
        Task updatedTask = updateTask(existingTask, taskData);
        return objectMapper.convertValue(updatedTask, TaskShortDTO.class);
    }

    private Task getTaskFromDB(UUID id) throws TaskNotFoundException{
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task is not found."));
    }

    private Task updateTask(Task existingTask, TaskUpdateDTO updatedTask) throws TaskServiceGeneralException{
        try {
            objectMapper.updateValue(existingTask, updatedTask);
            return taskRepository.save(existingTask);
        } catch (JsonMappingException e) {
            throw new TaskServiceGeneralException("An error has been occur when updating task information.");
        }
    }
}
