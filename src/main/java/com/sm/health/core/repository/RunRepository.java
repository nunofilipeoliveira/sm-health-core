package com.sm.health.core.repository;

import com.sm.health.core.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RunRepository extends JpaRepository<Run, String> {
    List<Run> findAllByOrderByCreatedAtDesc();
}
