package com.eud.ixtar.kanban;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eud.ixtar.kanban.column.KanbanColumn;
import com.eud.ixtar.kanban.column.KanbanColumnService;
import com.eud.ixtar.kanban.task.KanbanTask;
import com.eud.ixtar.kanban.task.KanbanTaskService;
import com.eud.ixtar.project.Project;
import com.eud.ixtar.project.ProjectService;

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

    public void setupKanbanForNewProject(Integer projectId) {
        // Obtener el objeto Project usando el ID
        Optional<Project> project = projectService.getProjectById(projectId);  // Suponiendo que tienes un servicio que lo obtiene
        
        // Definir las columnas predeterminadas
        KanbanColumn toDoColumn = new KanbanColumn();
        toDoColumn.setName("Pendiente");
        toDoColumn.setPosition(1);
        toDoColumn.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found"))); // Asignar el objeto Project, no el ID
    
        KanbanColumn inProgressColumn = new KanbanColumn();
        inProgressColumn.setName("En Proceso");
        inProgressColumn.setPosition(2);
        inProgressColumn.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found")));
    
        KanbanColumn completedColumn = new KanbanColumn();
        completedColumn.setName("Completadas");
        completedColumn.setPosition(3);
        completedColumn.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found")));
    
        // Guardar las columnas
        columnService.createColumn(toDoColumn);
        columnService.createColumn(inProgressColumn);
        columnService.createColumn(completedColumn);
    
        // Definir tareas predeterminadas
        KanbanTask task1 = new KanbanTask();
        task1.setTitle("Crear la estructura básica de la aplicación");
        task1.setDescription("Descripción de la tarea");
        task1.setColumn(toDoColumn);
        task1.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found")));
        taskService.createTask(task1);
    
        KanbanTask task2 = new KanbanTask();
        task2.setTitle("Añadir material cdk");
        task2.setDescription("Descripción de la tarea");
        task2.setColumn(inProgressColumn);
        task2.setProject(project.orElseThrow(() -> new IllegalStateException("Project not found")));
        taskService.createTask(task2);
    }
}
