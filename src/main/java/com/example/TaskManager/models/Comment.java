package com.example.TaskManager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "comments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    UUID id;
    @JoinColumn(nullable = false)
    @ManyToOne
    Task task;
    @JoinColumn(nullable = false)
    @ManyToOne
    User author;
    @Column(nullable = false)
    String text;
    @Column(nullable = false)
    Date createdAt;
}
