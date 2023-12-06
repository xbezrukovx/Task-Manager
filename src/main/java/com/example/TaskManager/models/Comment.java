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
    private UUID id;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Task task;
    @JoinColumn(nullable = false, name = "author")
    @ManyToOne
    private User author;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private Date createdAt;
}
