package com.eud.ixtar.brainstorming;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brainstorming")
public class BrainstormingController {

    private final BrainstormingService ideaService;

    public BrainstormingController(BrainstormingService ideaService) {
        this.ideaService = ideaService;
    }

    @PostMapping
    public ResponseEntity<Brainstorming> addIdea(@RequestBody Brainstorming idea) {
        return ResponseEntity.ok(ideaService.addIdea(idea));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<Brainstorming>> getIdeasByProject(@PathVariable Integer projectId) {
        return ResponseEntity.ok(ideaService.getIdeasByProject(projectId));
    }
}

