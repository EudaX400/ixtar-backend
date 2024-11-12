package com.eud.ixtar.project;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.eud.ixtar.brainstorming.Brainstorming;
import com.eud.ixtar.kanban.column.KanbanColumn;
import com.eud.ixtar.users.User;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private String type;
    private String company;

    @Column(name = "finish_at")
    private Date finishAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KanbanColumn> kanbanColumns;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Brainstorming> brainstormings;

    @ManyToMany
    @JoinTable(
        name = "project_members",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public User getOwner() {  
        return owner;
    }

    public void setOwner(User owner) {  
        this.owner = owner;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type=type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company){
        this.company=company;
    }

    public Date getFinishAt(){
        return finishAt;
    }

    public void setFinishAt(Date finishAt){
        this.finishAt=finishAt;
    }

    public List<KanbanColumn> getKanbanColumn(){
        return kanbanColumns;
    }

    public List<Brainstorming> geBrainstormings(){
        return brainstormings;
    }

}

