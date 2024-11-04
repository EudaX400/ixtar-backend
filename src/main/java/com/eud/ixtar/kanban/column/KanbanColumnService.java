package com.eud.ixtar.kanban.column;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class KanbanColumnService {
    private final KanbanColumnRepository columnRepository;

    public KanbanColumnService(KanbanColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public KanbanColumn createColumn(KanbanColumn column) {
        return columnRepository.save(column);
    }

    public List<KanbanColumn> getColumnsByProject(Integer projectId) {
        return columnRepository.findByProjectIdOrderByPositionAsc(projectId);
    }

    public KanbanColumn updateColumn(KanbanColumn column) {
        return columnRepository.save(column);
    }

    public void deleteColumn(Integer columnId) {
        columnRepository.deleteById(columnId);
    }
}
