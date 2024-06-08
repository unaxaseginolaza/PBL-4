package com.example.pbl4.preProcessedMaterial;

import com.example.pbl4.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PreProcessedMaterialService {
    private final PreProcessedMaterialRepository preProcessedMaterialRepository;

    @Autowired
    public PreProcessedMaterialService(PreProcessedMaterialRepository preProcessedMaterialRepository) {
        this.preProcessedMaterialRepository = preProcessedMaterialRepository;
    }

    public List<PreProcessedMaterial> getPreProcessedMaterials() {
        return this.preProcessedMaterialRepository.findAll();
    }

    public PreProcessedMaterial findPreProcessedMaterialById(Long id) {
        return preProcessedMaterialRepository.findById(id).orElseThrow(() -> new IllegalStateException("PreProcessedMaterial not found"));
    }

    public void newPreProcessedMaterial(PreProcessedMaterial preProcessedMaterial) {
        Optional<PreProcessedMaterial> res = preProcessedMaterialRepository.findPreProcessedMaterialByQuantity(preProcessedMaterial.getQuantity());
        if (res.isPresent() && preProcessedMaterial.getId() == null) {
            throw new IllegalStateException("Ya existe un empleado con name");
        }
        preProcessedMaterialRepository.save(preProcessedMaterial);
    }

    public void deletePreProcessedMaterial(Long id) {
        boolean exists = preProcessedMaterialRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un PreProcessedMaterial con esa id");
        }
        preProcessedMaterialRepository.deleteById(id);
    }

    public void updatePreProcessedMaterial(PreProcessedMaterial updatedPreProcessedMaterial) {
        PreProcessedMaterial existingPreProcessedMaterial = findPreProcessedMaterialById(updatedPreProcessedMaterial.getId());

        existingPreProcessedMaterial.setQuantity(updatedPreProcessedMaterial.getQuantity());
        existingPreProcessedMaterial.setType(updatedPreProcessedMaterial.getType());

        preProcessedMaterialRepository.save(existingPreProcessedMaterial);
    }
}
