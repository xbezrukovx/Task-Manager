package com.example.TaskManager.repositories;

import com.example.TaskManager.models.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {
    @Query(value = "SELECT * FROM tasks WHERE author_id = ?1",
            nativeQuery = true
    )
    List<Task> findByAuthor(UUID authorId);
}
