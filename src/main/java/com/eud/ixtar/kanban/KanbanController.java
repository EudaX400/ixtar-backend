package com.eud.ixtar.kanban;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eud.ixtar.kanban.column.KanbanColumn;
import com.eud.ixtar.kanban.column.KanbanColumnService;
import com.eud.ixtar.kanban.task.KanbanTask;
import com.eud.ixtar.kanban.task.KanbanTaskService;

@RestController
@RequestMapping("/api/kanban")
public class KanbanController {

    private final KanbanColumnService columnService;
    private final KanbanTaskService taskService;
    private final KanbanSetUpService kanbanSetupService;

    public KanbanController(KanbanColumnService columnService, KanbanTaskService taskService, KanbanSetUpService kanbanSetupService) {
        this.columnService = columnService;
        this.taskService = taskService;
        this.kanbanSetupService = kanbanSetupService;
    }

    @PostMapping("/columns")
    public ResponseEntity<KanbanColumn> createColumn(@RequestBody KanbanColumn column) {
        KanbanColumn createdColumn = columnService.createColumn(column);
        return ResponseEntity.status(201).body(createdColumn); // Retorna el objeto creado con estado 201 (Creado)
    }

    @PostMapping("/tasks")
    public ResponseEntity<KanbanTask> createTask(@RequestBody KanbanTask task) {
        KanbanTask createdTask = taskService.createTask(task);
        return ResponseEntity.status(201).body(createdTask); // Retorna el objeto creado con estado 201
    }

    @GetMapping("/columns/{projectId}")
    public ResponseEntity<List<KanbanColumn>> getColumnsByProject(@PathVariable Integer projectId) {
        List<KanbanColumn> columns = columnService.getColumnsByProject(projectId);
        return ResponseEntity.ok(columns); // Retorna las columnas con estado 200
    }

    @GetMapping("/tasks/{projectId}/{columnId}")
    public ResponseEntity<List<KanbanTask>> getTasksByColumn(@PathVariable Integer projectId, @PathVariable Integer columnId) {
        List<KanbanTask> tasks = taskService.getTasksByColumn(projectId, columnId);
        return ResponseEntity.ok(tasks); // Retorna las tareas con estado 200
    }

    @PostMapping("/setup/{projectId}")
    public ResponseEntity<Void> setupKanbanForNewProject(@PathVariable Integer projectId) {
        kanbanSetupService.setupKanbanForNewProject(projectId);
        return ResponseEntity.status(201).build(); // Retorna estado 201 sin cuerpo
    }

    // Método para actualizar una columna
    @PutMapping("/columns/{columnId}")
    public ResponseEntity<KanbanColumn> updateColumn(@PathVariable Integer columnId, @RequestBody KanbanColumn column) {
        column.setId(columnId);
        KanbanColumn updatedColumn = columnService.updateColumn(column);
        return ResponseEntity.ok(updatedColumn);
    }

    // Método para eliminar una columna
    @DeleteMapping("/columns/{columnId}")
    public ResponseEntity<Void> deleteColumn(@PathVariable Integer columnId) {
        columnService.deleteColumn(columnId);
        return ResponseEntity.noContent().build(); // Retorna estado 204 (sin contenido)
    }

    // Método para actualizar una tarea
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<KanbanTask> updateTask(@PathVariable Integer taskId, @RequestBody KanbanTask task) {
        task.setId(taskId);
        KanbanTask updatedTask = taskService.updateTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    // Método para eliminar una tarea
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build(); // Retorna estado 204 (sin contenido)
    }

@PutMapping("/tasks/reorder")
    public ResponseEntity<Void> reorderTask(@RequestParam Integer taskId, @RequestParam Integer newPosition) {
        taskService.reorderTask(taskId, newPosition);
        return ResponseEntity.ok().build();
    }

    // Transferir tarea a otra columna
    @PutMapping("/tasks/transfer")
    public ResponseEntity<Void> transferTask(
            @RequestParam Integer taskId,
            @RequestParam Integer newColumnId,
            @RequestParam Integer newPosition) {
        taskService.transferTask(taskId, newColumnId, newPosition);
        return ResponseEntity.ok().build();
    }

    // Reordenar columna en el tablero
    @PutMapping("/columns/reorder")
    public ResponseEntity<Void> reorderColumn(@RequestParam Integer columnId, @RequestParam Integer newPosition) {
        columnService.reorderColumn(columnId, newPosition);
        return ResponseEntity.ok().build();
    }

    // Similar endpoints for updating and deleting columns and tasks
}

