package com.eud.ixtar.kanban;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eud.ixtar.kanban.column.KanbanColumn;
import com.eud.ixtar.kanban.column.KanbanColumnService;
import com.eud.ixtar.kanban.task.KanbanTask;
import com.eud.ixtar.kanban.task.KanbanTaskService;
import com.eud.ixtar.project.Project;
import com.eud.ixtar.project.ProjectService;

import jakarta.transaction.Transactional;

@Service
public class KanbanSetUpService {
    private final KanbanColumnService columnService;
    private final KanbanTaskService taskService;
    private final ProjectService projectService;

    public KanbanSetUpService(KanbanColumnService columnService, KanbanTaskService taskService, ProjectService projectService) {
        this.columnService = columnService;
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @Transactional
    public void setupKanbanForNewProject(Integer projectId) {
        if (!columnService.getColumnsByProject(projectId).isEmpty()) {
            throw new IllegalStateException("El proyecto ya tiene columnas asignadas.");
        }

        Optional<Project> project = projectService.getProjectById(projectId); 
        

        KanbanColumn toDoColumn = new KanbanColumn();
        toDoColumn.setName("Pendiente");
        toDoColumn.setPosition(1);
        toDoColumn.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found"))); 
    
        KanbanColumn inProgressColumn = new KanbanColumn();
        inProgressColumn.setName("En Proceso");
        inProgressColumn.setPosition(2);
        inProgressColumn.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found")));
    
        KanbanColumn completedColumn = new KanbanColumn();
        completedColumn.setName("Completadas");
        completedColumn.setPosition(3);
        completedColumn.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found")));
    
   
        columnService.createColumn(toDoColumn);
        columnService.createColumn(inProgressColumn);
        columnService.createColumn(completedColumn);
    

        KanbanTask task1 = new KanbanTask();
        task1.setTitle("Crear la estructura básica de la aplicación");
        task1.setDescription("Descripción de la tarea");
        task1.setColumn(toDoColumn);
        task1.setPosition(1);
        task1.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found")));
        taskService.createTask(task1);
    
        KanbanTask task2 = new KanbanTask();
        task2.setTitle("Añadir material cdk");
        task2.setDescription("Descripción de la tarea");
        task2.setColumn(inProgressColumn);
        task1.setPosition(1);
        task2.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found")));
        taskService.createTask(task2);

        KanbanTask task3 = new KanbanTask();
        task2.setTitle("Organizar cada parte del proyecto entre el equipo");
        task2.setDescription("Descripción de la tarea");
        task2.setColumn(completedColumn);
        task1.setPosition(1);
        task2.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found")));
        taskService.createTask(task3);
    }
}
