package com.eud.ixtar.project;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Project createProject(Project project) {
        // Verifica si ya existe un proyecto con el mismo nombre y dueño
        if (projectRepository.existsByNameAndOwner(project.getName(), project.getOwner())) {
            throw new IllegalStateException("Ya existe un proyecto con este nombre para el mismo dueño.");
        }
        return projectRepository.save(project);
    }

    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    public List<Project> getProjectsByUser(Integer ownerId) {
        return projectRepository.findByOwnerId(ownerId);
    }

    public Optional<Project> updateProject(Integer id, Project updatedProject) {
        return projectRepository.findById(id).map(existingProject -> {
            existingProject.setName(updatedProject.getName());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setType(updatedProject.getType());
            existingProject.setCompany(updatedProject.getCompany());
            existingProject.setFinishAt(updatedProject.getFinishAt());
            
            return projectRepository.save(existingProject);
        });
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }
}