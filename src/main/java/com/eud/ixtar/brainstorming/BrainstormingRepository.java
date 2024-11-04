package com.eud.ixtar.brainstorming;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrainstormingRepository extends JpaRepository<Brainstorming, Integer> {
    List<Brainstorming> findByProjectIdOrderByCreatedAtDesc(Integer projectId);
}
