package com.example.TaskManager.models;

import com.example.TaskManager.enums.TaskPriority;
import com.example.TaskManager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tasks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
    @JoinColumn(nullable = false, name = "author_id")
    @ManyToOne
    private User author;
    @JoinColumn(name = "responsible_id")
    @ManyToOne
    User responsible;

}
