package com.eud.ixtar.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    
    @GetMapping
    public List<Project> getAllProjects() {
        return null;
        // lógica para obtener todos los proyectos
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return project;
        // lógica para crear un proyecto
    }
}