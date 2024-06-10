package com.example.pbl4.processedMaterial;

import com.example.pbl4.preProcessedMaterial.PreProcessedMaterial;
import com.example.pbl4.preProcessedMaterial.PreProcessedMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "processedMaterial")
public class ProcessedMaterialController {
    private final ProcessedMaterialService processedMaterialService;
    private final PreProcessedMaterialService preProcessedMaterialService;

    @Autowired
    public ProcessedMaterialController(ProcessedMaterialService processedMaterialService, PreProcessedMaterialService preProcessedMaterialService) {
        this.processedMaterialService = processedMaterialService;
        this.preProcessedMaterialService = preProcessedMaterialService;
    }

    @GetMapping("/list")
    public String getProcessedMaterials(Model model) {
        List<ProcessedMaterial> processedMaterials = processedMaterialService.getProcessedMaterials();
        model.addAttribute("processedMaterials", processedMaterials);
        return "processedMaterial/processedMaterial_list"; // Retorna la vista processedMaterial/list.html
    }

    @PostMapping("/create")
    public String createProcessedMaterial(@ModelAttribute ProcessedMaterial processedMaterial, Model model) {
        Long preProcessedMaterialId = processedMaterial.getPreProcessedMaterial().getId();

        // Asignar el material sin proporcionar si se proporciona
        if (preProcessedMaterialId != null) {
            PreProcessedMaterial preProcessedMaterial = preProcessedMaterialService.findPreProcessedMaterialById(preProcessedMaterialId);
            processedMaterial.setPreProcessedMaterial(preProcessedMaterial);
            if (preProcessedMaterial.getQuantity() - processedMaterial.getQuantity() >= 0){
                preProcessedMaterial.setQuantity(preProcessedMaterial.getQuantity() - processedMaterial.getQuantity());
                preProcessedMaterialService.updatePreProcessedMaterial(preProcessedMaterial);
            }else {
                return "redirect:/processedMaterial/form";
            }

        }

        // Guardar el nuevo empleado
        processedMaterialService.newProcessedMaterial(processedMaterial);
        return "redirect:/processedMaterial/list"; // Redirige a la lista de empleados
    }

    @GetMapping("/edit/{id}")
    public String updateProcessedMaterialForm(@PathVariable("id") Long id, Model model) {
        ProcessedMaterial processedMaterial = processedMaterialService.findProcessedMaterialById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("processedMaterial", processedMaterial);
        model.addAttribute("preProcessedMaterials", preProcessedMaterialService.getPreProcessedMaterials());
        return "processedMaterial/processedMaterial_form"; // Retorna la vista processedMaterial/edit.html
    }

    @PostMapping("/update")
    public String updateProcessedMaterial(@ModelAttribute ProcessedMaterial processedMaterial, Model model) {
        processedMaterialService.newProcessedMaterial(processedMaterial);
        return "redirect:/processedMaterial/list"; //Redirige a la lista de material procesados
    }

    @PostMapping("/delete/{id}")
    public String deleteProcessedMaterial(@PathVariable("id") Long id) {
        processedMaterialService.deleteProcessedMaterial(id);
        return "redirect:/processedMaterial/list"; // Redirige a la lista de material procesados
    }

    @GetMapping("/form")
    public String showProcessedMaterialForm(Model model) {
        model.addAttribute("processedMaterial", new ProcessedMaterial());
        model.addAttribute("preProcessedMaterials", preProcessedMaterialService.getPreProcessedMaterials());
        return "processedMaterial/processedMaterial_form"; // Retorna la vista processedMaterial/processedMaterial_form.html
    }
}
