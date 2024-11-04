package com.eud.ixtar.brainstorming;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BrainstormingService {

    private final BrainstormingRepository ideaRepository;

    public BrainstormingService(BrainstormingRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public Brainstorming addIdea(Brainstorming idea) {
        return ideaRepository.save(idea);
    }

    public List<Brainstorming> getIdeasByProject(Integer projectId) {
        return ideaRepository.findByProjectIdOrderByCreatedAtDesc(projectId);
    }
}