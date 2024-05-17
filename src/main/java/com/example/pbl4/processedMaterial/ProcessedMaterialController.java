package com.example.pbl4.processedMaterial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/processedMaterial")
public class ProcessedMaterialController {
    private final ProcessedMaterialService processedMaterialService;

    @Autowired
    public ProcessedMaterialController(ProcessedMaterialService processedMaterialService) {
        this.processedMaterialService = processedMaterialService;
    }

    @GetMapping
    public List<ProcessedMaterial> getProcessedMaterials() {
        return processedMaterialService.getProcessedMaterials();
    }

    @PostMapping
    public ResponseEntity<Object> createProcessedMaterials(@RequestBody ProcessedMaterial ProcessedMaterial) {
        return this.processedMaterialService.newProcessedMaterial(ProcessedMaterial);
    }

    @PutMapping
    public ResponseEntity<Object> updateProcessedMaterials(@RequestBody ProcessedMaterial ProcessedMaterial) {
        return this.processedMaterialService.newProcessedMaterial(ProcessedMaterial);
    }

    @DeleteMapping(path = "/{ProcessedMaterialsId}/delete")
    public ResponseEntity<Object> deleteProcessedMaterials(@PathVariable("ProcessedMaterialsId") Long id) {
        return this.processedMaterialService.deleteProcessedMaterial(id);
    }
}
