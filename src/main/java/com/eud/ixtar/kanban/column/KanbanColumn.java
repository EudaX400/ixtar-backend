package com.eud.ixtar.kanban.column;

import jakarta.persistence.*;
import java.util.List;

import com.eud.ixtar.kanban.task.KanbanTask;
import com.eud.ixtar.project.Project;

@Entity
@Table(name = "kanban_columns")
public class KanbanColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KanbanTask> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project2) {
        this.project = project2;
    }

    public List<KanbanTask> getKanbanTask(){
            return tasks;
    }

    public void setKanbanTask(List<KanbanTask> tasks) {
        for (KanbanTask task : tasks) {
            task.setColumn(this);
        }
        this.tasks = tasks;
    }

    public void setId(Integer columnId) {
        this.id=columnId;
    }
}

