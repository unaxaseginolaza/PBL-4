package com.example.pbl4.processedMaterial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessedMaterialService {
    private final ProcessedMaterialRepository processedMaterialRepository;

    @Autowired
    public ProcessedMaterialService(ProcessedMaterialRepository processedMaterialRepository) {
        this.processedMaterialRepository = processedMaterialRepository;
    }

    public List<ProcessedMaterial> getProcessedMaterials() {
        return this.processedMaterialRepository.findAll();
    }

    public ProcessedMaterial findProcessedMaterialById(Long id) {
        return processedMaterialRepository.findById(id).orElseThrow(() -> new IllegalStateException("ProcessedMaterial not found"));
    }

    public void newProcessedMaterial(ProcessedMaterial processedMaterial) {
        Optional<ProcessedMaterial> res = processedMaterialRepository.findProcessedMaterialByQuantity(processedMaterial.getQuantity());
        if (res.isPresent() && processedMaterial.getId() == null) {
            throw new IllegalStateException("Ya existe un empleado con name");
        }
        processedMaterialRepository.save(processedMaterial);
    }

    public void deleteProcessedMaterial(Long id) {
        boolean exists = processedMaterialRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un ProcessedMaterial con esa id");
        }
        processedMaterialRepository.deleteById(id);
    }

    public void updateProcessedMaterial(ProcessedMaterial updatedProcessedMaterial) {
        ProcessedMaterial existingProcessedMaterial = findProcessedMaterialById(updatedProcessedMaterial.getId());

        existingProcessedMaterial.setQuantity(updatedProcessedMaterial.getQuantity());
        existingProcessedMaterial.setPreProcessedMaterial(updatedProcessedMaterial.getPreProcessedMaterial()); // En este punto, la contraseña ya debería estar codificada

        processedMaterialRepository.save(existingProcessedMaterial);
    }
}
