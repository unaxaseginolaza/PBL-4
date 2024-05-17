package com.example.pbl4.preProcessedMaterial;

import com.example.pbl4.preProcessedMaterial.PreProcessedMaterial;
import com.example.pbl4.preProcessedMaterial.PreProcessedMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "api/v1/preProcessedMaterial")
public class PreProcessedMaterialController {
    private final PreProcessedMaterialService preProcessedMaterialService;

    @Autowired
    public PreProcessedMaterialController(PreProcessedMaterialService preProcessedMaterialService) {
        this.preProcessedMaterialService = preProcessedMaterialService;
    }

    @GetMapping
    public List<PreProcessedMaterial> getPreProcessedMaterials() {
        return preProcessedMaterialService.getPreProcessedMaterials();
    }

    @PostMapping
    public ResponseEntity<Object> createPreProcessedMaterials(@RequestBody PreProcessedMaterial preProcessedMaterial) {
        return this.preProcessedMaterialService.newPreProcessedMaterial(preProcessedMaterial);
    }

    @PutMapping
    public ResponseEntity<Object> updatePreProcessedMaterials(@RequestBody PreProcessedMaterial preProcessedMaterial) {
        return this.preProcessedMaterialService.newPreProcessedMaterial(preProcessedMaterial);
    }

    @DeleteMapping(path = "/{PreProcessedMaterialsId}/delete")
    public ResponseEntity<Object> deletePreProcessedMaterials(@PathVariable("PreProcessedMaterialsId") Long id) {
        return this.preProcessedMaterialService.deletePreProcessedMaterial(id);
    }
}
