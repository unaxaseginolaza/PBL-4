package com.example.pbl4.processedMaterial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedMaterialRepository extends JpaRepository<ProcessedMaterial, Long> {
    Optional<ProcessedMaterial> findProcessedMaterialByQuantity(Float quantity);
}
