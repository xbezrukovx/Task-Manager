package com.example.TaskManager.services;

import com.example.TaskManager.dto.tasks.request.TaskCreateDTO;
import com.example.TaskManager.dto.tasks.request.TaskUpdateDTO;
import com.example.TaskManager.dto.tasks.response.TaskShortDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author Denis Bezrukov
 * @since 12/07/2023
 */
public interface TaskService {
    /**
     * This method creates a new task by given information from {@link TaskCreateDTO}
     * and sets provided user as author of new task.
     *
     * @param taskData An information about the task.
     * @param userId An user identifier.
     */
    void createTask(TaskCreateDTO taskData, UUID userId);

    /**
     * This method returns a task by provided identifier.
     *
     * @param taskId A task identifier.
     * @return {@link TaskShortDTO} - An information about the task.
     */
    TaskShortDTO getTask(UUID taskId);

    /**
     * This method returns a list of tasks by provided author identifier.
     *
     * @param userId A task identifier.
     * @return A list of {@link TaskShortDTO} - An information about the task.
     */
    List<TaskShortDTO> getTasksByUserId(UUID userId);

    /**
     * This method updates an information about task by provided task identifier.
     *
     * @param taskData  An information about the task.
     * @param taskId A task identifier.
     * @return {@link TaskShortDTO} - An information about the task.
     */
    TaskShortDTO modifyTask(TaskUpdateDTO taskData, UUID taskId);

    /**
     * This method sets provided user as responsible for the task.
     *
     * @param taskId A task identifier.
     * @param responsibleUserId - An user responsible for task.
     * @return {@link TaskShortDTO} - An information about the task.
     */
    TaskShortDTO setResponsible(UUID taskId, UUID responsibleUserId);
}
