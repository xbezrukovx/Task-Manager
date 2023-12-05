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
    UUID id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TaskStatus status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TaskPriority priority;
    @JoinColumn(nullable = false, name = "author_id")
    @ManyToOne
    User author;
    @JoinColumn(name = "responsible_id")
    @ManyToOne
    User responsible;

}
