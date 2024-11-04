package com.eud.ixtar.kanban.column;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KanbanColumnRepository extends JpaRepository<KanbanColumn, Integer> {
    List<KanbanColumn> findByProjectIdOrderByPositionAsc(Integer projectId);
}
