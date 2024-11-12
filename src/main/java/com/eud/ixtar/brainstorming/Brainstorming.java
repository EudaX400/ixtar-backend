package com.eud.ixtar.brainstorming;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.eud.ixtar.project.Project;
import com.eud.ixtar.users.User;

@Entity
@Table(name = "brainstorming_ideas")
public class Brainstorming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String idea;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Integer getId(){
        return id;
    }

    public String getIdea(){
        return idea;
    }

    public void setIdea(String idea){
        this.idea = idea;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public Project getProject(){
        return project;
    }

    public User getUser(){
        return user;
    }

    

}
