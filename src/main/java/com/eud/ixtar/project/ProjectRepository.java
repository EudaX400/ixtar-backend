package com.eud.ixtar.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByOwnerId(Integer ownerId); 
}

