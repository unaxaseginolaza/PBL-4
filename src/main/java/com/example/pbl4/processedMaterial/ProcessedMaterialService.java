package com.example.pbl4.processedMaterial;

import com.example.pbl4.processedMaterial.ProcessedMaterial;
import com.example.pbl4.processedMaterial.ProcessedMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessedMaterialService {
    private final ProcessedMaterialRepository processedMaterialRepository;
    HashMap<String, Object> datos;

    @Autowired
    public ProcessedMaterialService(ProcessedMaterialRepository processedMaterialRepository) {
        this.processedMaterialRepository = processedMaterialRepository;
    }

    public List<ProcessedMaterial> getProcessedMaterials() {
        return this.processedMaterialRepository.findAll();
    }

    public ResponseEntity<Object> newProcessedMaterial(ProcessedMaterial processedMaterial) {
        Optional<ProcessedMaterial> res = processedMaterialRepository.findProcessedMaterialByQuantity(processedMaterial.getQuantity());
        datos = new HashMap<>();

        if (res.isPresent() && processedMaterial.getId()==null) {
            //throw new IllegalStateException("ya existe el producto");
            datos.put("error", true);
            datos.put("message", "Ya existe un ProcessedMaterial con ese nombre");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        if (processedMaterial.getId()!= null) {
            datos.put("message", "Se actualizo con exito");
        }
        processedMaterialRepository.save(processedMaterial);
        datos.put("data", processedMaterial);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteProcessedMaterial(Long id) {
        boolean exists = this.processedMaterialRepository.existsById(id);
        datos = new HashMap<>();

        if (!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un ProcessedMaterial con esa id");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        processedMaterialRepository.deleteById(id);
        datos.put("message", "ProcessedMaterial eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
