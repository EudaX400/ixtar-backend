package com.eud.ixtar.kanban.task;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class KanbanTaskService {

    private final KanbanTaskRepository taskRepository;

    public KanbanTaskService(KanbanTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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
}
