package com.eud.ixtar.kanban;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    public KanbanController(KanbanColumnService columnService, KanbanTaskService taskService) {
        this.columnService = columnService;
        this.taskService = taskService;
    }

    @PostMapping("/columns")
    public ResponseEntity<KanbanColumn> createColumn(@RequestBody KanbanColumn column) {
        return ResponseEntity.ok(columnService.createColumn(column));
    }

    @PostMapping("/tasks")
    public ResponseEntity<KanbanTask> createTask(@RequestBody KanbanTask task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/columns/{projectId}")
    public ResponseEntity<List<KanbanColumn>> getColumnsByProject(@PathVariable Integer projectId) {
        return ResponseEntity.ok(columnService.getColumnsByProject(projectId));
    }

    @GetMapping("/tasks/{projectId}/{columnId}")
    public ResponseEntity<List<KanbanTask>> getTasksByColumn(@PathVariable Integer projectId, @PathVariable Integer columnId) {
        return ResponseEntity.ok(taskService.getTasksByColumn(projectId, columnId));
    }

    // Similar endpoints for updating and deleting columns and tasks
}

