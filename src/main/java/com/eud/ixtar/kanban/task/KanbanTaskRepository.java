package com.eud.ixtar.kanban.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanTaskRepository extends JpaRepository<KanbanTask, Integer> {
    List<KanbanTask> findByProjectIdAndColumnIdOrderByPositionAsc(Integer projectId, Integer columnId);
}
