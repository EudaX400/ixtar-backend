package com.eud.ixtar.kanban.task;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

import com.eud.ixtar.kanban.column.KanbanColumn;
import com.eud.ixtar.project.Project;

@Entity
@Table(name = "kanban_tasks")
public class KanbanTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "assigned_to")
    private Integer assignedTo;

    @Column(nullable = false)
    private Integer position;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "due_date")
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "column_id", nullable = false)
    private KanbanColumn column;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public KanbanColumn getColumn() {
        return column;
    }

    public void setColumn(KanbanColumn column) {
        this.column = column;
    }

    public Project getProject() {
        return project;
    }

}