package com.example.pbl4.preProcessedMaterial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PreProcessedMaterialService {
    private final PreProcessedMaterialRepository preProcessedMaterialRepository;
    HashMap<String, Object> datos;

    @Autowired
    public PreProcessedMaterialService(PreProcessedMaterialRepository preProcessedMaterialRepository) {
        this.preProcessedMaterialRepository = preProcessedMaterialRepository;
    }

    public List<PreProcessedMaterial> getPreProcessedMaterials() {
        return this.preProcessedMaterialRepository.findAll();
    }

    public ResponseEntity<Object> newPreProcessedMaterial(PreProcessedMaterial preProcessedMaterial) {
        Optional<PreProcessedMaterial> res = preProcessedMaterialRepository.findPreProcessedMaterialByQuantity(preProcessedMaterial.getQuantity());
        datos = new HashMap<>();

        if (res.isPresent() && preProcessedMaterial.getId()==null) {
            //throw new IllegalStateException("ya existe el producto");
            datos.put("error", true);
            datos.put("message", "Ya existe un PreProcessedMaterial con ese nombre");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        if (preProcessedMaterial.getId()!= null) {
            datos.put("message", "Se actualizo con exito");
        }
        preProcessedMaterialRepository.save(preProcessedMaterial);
        datos.put("data", preProcessedMaterial);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deletePreProcessedMaterial(Long id) {
        boolean exists = this.preProcessedMaterialRepository.existsById(id);
        datos = new HashMap<>();

        if (!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un PreProcessedMaterial con esa id");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        preProcessedMaterialRepository.deleteById(id);
        datos.put("message", "PreProcessedMaterial eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
