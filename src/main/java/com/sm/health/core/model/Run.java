package com.sm.health.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Entity
@Table(name = "run")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Run {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    
    @NotBlank(message = "Date is required")
    @Column(name = "date", nullable = false)
    private String date;
    
    @NotNull(message = "Time is required")
    @Min(value = 1, message = "Time must be at least 1 second")
    @Column(name = "time", nullable = false)
    private Integer time; // em segundos
    
    @NotNull(message = "Distance is required")
    @DecimalMin(value = "0.1", message = "Distance must be at least 0.1 km")
    @Column(name = "distance", nullable = false)
    private Double distance; // em km
    
    @Size(max = 500, message = "Notes must be less than 500 characters")
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
