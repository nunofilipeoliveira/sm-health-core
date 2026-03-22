package com.sm.health.core.service;

import com.sm.health.core.model.Run;
import com.sm.health.core.repository.RunRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RunService {
    
    private final RunRepository runRepository;
    private final Logger logger = LoggerFactory.getLogger(RunService.class);
    
    public List<Run> getAllRuns() {
        logger.debug("Getting all runs from repository");
        return runRepository.findAllByOrderByCreatedAtDesc();
    }
    
    public Optional<Run> getRunById(String id) {
        logger.debug("Getting run with id: {} from repository", id);
        return runRepository.findById(id);
    }
    
    @Transactional
    public Run createRun(Run run) {
        logger.debug("Creating run: {}", run);
        run.setCreatedAt(LocalDateTime.now());
        Run savedRun = runRepository.save(run);
        logger.debug("Saved run: {}", savedRun);
        return savedRun;
    }
    
    @Transactional
    public Run updateRun(Run run) {
        logger.debug("Updating run: {}", run);
        Run updatedRun = runRepository.save(run);
        logger.debug("Updated run: {}", updatedRun);
        return updatedRun;
    }
    
    @Transactional
    public void deleteRun(String id) {
        logger.debug("Deleting run with id: {}", id);
        runRepository.deleteById(id);
        logger.debug("Deleted run with id: {}", id);
    }
    
    public List<Run> getRecentRuns(int limit) {
        logger.debug("Getting recent runs with limit: {}", limit);
        List<Run> allRuns = runRepository.findAllByOrderByCreatedAtDesc();
        List<Run> recentRuns = allRuns.stream().limit(limit).toList();
        logger.debug("Found {} recent runs", recentRuns.size());
        return recentRuns;
    }
}
