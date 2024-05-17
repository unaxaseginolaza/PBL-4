package com.example.pbl4.preProcessedMaterial;

import com.example.pbl4.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreProcessedMaterialRepository extends JpaRepository<PreProcessedMaterial, Long> {
    Optional<PreProcessedMaterial> findPreProcessedMaterialByQuantity(Float quantity);
}
