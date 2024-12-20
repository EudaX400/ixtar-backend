package com.eud.ixtar.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eud.ixtar.users.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByOwnerId(Integer ownerId); 
    Optional<Project> findByIdAndOwnerId(Integer id, Integer ownerId);
    boolean existsByNameAndOwner(String name, User owner);
}

