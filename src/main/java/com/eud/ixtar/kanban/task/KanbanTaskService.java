package com.eud.ixtar.kanban.task;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eud.ixtar.kanban.column.KanbanColumn;
import com.eud.ixtar.kanban.column.KanbanColumnService;
@Service
public class KanbanTaskService {

    private final KanbanTaskRepository taskRepository;
    private final KanbanColumnService columnService;

    public KanbanTaskService(KanbanTaskRepository taskRepository, KanbanColumnService columnService) {
        this.taskRepository = taskRepository;
        this.columnService = columnService;
    }

    public KanbanTask createTask(KanbanTask task) {
        return taskRepository.save(task);
    }

    public List<KanbanTask> getTasksByColumn(Integer projectId, Integer columnId) {
        return taskRepository.findByProjectIdAndColumnIdOrderByPositionAsc(projectId, columnId);
    }

    public KanbanTask updateTask(KanbanTask task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }

    public void reorderTask(Integer taskId, Integer newPosition) {
        KanbanTask task = taskRepository.findById(taskId).orElseThrow();
        task.setPosition(newPosition);
        taskRepository.save(task);
    }
    
    public void transferTask(Integer taskId, Integer newColumnId, Integer newPosition) {
        Optional<KanbanColumn> column = columnService.getColumnById(newColumnId);
        KanbanTask task = taskRepository.findById(taskId).orElseThrow();
        task.setColumn(column.orElseThrow(() -> new IllegalStateException("Column not found")));
        task.setPosition(newPosition);
        taskRepository.save(task);
    }
    
}
