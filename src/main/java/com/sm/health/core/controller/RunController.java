package com.sm.health.core.controller;

import com.sm.health.core.model.Run;
import com.sm.health.core.service.RunService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/runs")
@RequiredArgsConstructor
public class RunController {
    
    private final RunService runService;
    private final Logger logger = LoggerFactory.getLogger(RunController.class);
    
    @GetMapping
    public ResponseEntity<List<Run>> getAllRuns() {
        logger.info("Getting all runs");
        List<Run> runs = runService.getAllRuns();
        logger.info("Found {} runs", runs.size());
        return ResponseEntity.ok(runs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Run> getRunById(@PathVariable String id) {
        logger.info("Getting run with id: {}", id);
        return runService.getRunById(id)
                .map(run -> {
                    logger.info("Found run: {}", run);
                    return ResponseEntity.ok(run);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Run> createRun(@RequestBody @Valid Run run) {
        logger.info("Creating run: {}", run);
        Run createdRun = runService.createRun(run);
        logger.info("Created run: {}", createdRun);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRun);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Run> updateRun(@PathVariable String id, @RequestBody @Valid Run run) {
        logger.info("Updating run with id: {}, data: {}", id, run);
        if (!runService.getRunById(id).isPresent()) {
            logger.warn("Run with id: {} not found", id);
            return ResponseEntity.notFound().build();
        }
        run.setId(id);
        Run updatedRun = runService.updateRun(run);
        logger.info("Updated run: {}", updatedRun);
        return ResponseEntity.ok(updatedRun);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRun(@PathVariable String id) {
        logger.info("Deleting run with id: {}", id);
        if (runService.getRunById(id).isPresent()) {
            runService.deleteRun(id);
            logger.info("Deleted run with id: {}", id);
            return ResponseEntity.noContent().build();
        }
        logger.warn("Run with id: {} not found", id);
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/recent")
    public ResponseEntity<List<Run>> getRecentRuns(@RequestParam(defaultValue = "5") int limit) {
        logger.info("Getting recent runs with limit: {}", limit);
        List<Run> recentRuns = runService.getRecentRuns(limit);
        logger.info("Found {} recent runs", recentRuns.size());
        return ResponseEntity.ok(recentRuns);
    }
}
