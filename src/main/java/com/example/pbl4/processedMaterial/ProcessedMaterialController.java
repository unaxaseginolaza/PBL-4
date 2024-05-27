package com.example.pbl4.processedMaterial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "processedMaterial")
public class ProcessedMaterialController {
    private final ProcessedMaterialService processedMaterialService;

    @Autowired
    public ProcessedMaterialController(ProcessedMaterialService processedMaterialService) {
        this.processedMaterialService = processedMaterialService;
    }

    @GetMapping
    public String getProcessedMaterials(Model model) {
        List<ProcessedMaterial> processedMaterials = processedMaterialService.getProcessedMaterials();
        model.addAttribute("processedMaterials", processedMaterials);
        return "processedMaterial/list"; // Retorna la vista processedMaterial/list.html
    }

    @GetMapping("/new")
    public String createProcessedMaterialForm(Model model) {
        model.addAttribute("processedMaterial", new ProcessedMaterial());
        return "processedMaterial/new"; // Retorna la vista processedMaterial/new.html
    }

    @PostMapping
    public String createProcessedMaterial(@ModelAttribute ProcessedMaterial processedMaterial, Model model) {
        processedMaterialService.newProcessedMaterial(processedMaterial);
        return "redirect:/processedMaterial"; // Redirige a la lista de clientes
    }

    @GetMapping("/edit/{id}")
    public String updateProcessedMaterialForm(@PathVariable("id") Long id, Model model) {
        ProcessedMaterial processedMaterial = processedMaterialService.findProcessedMaterialById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("processedMaterial", processedMaterial);
        return "processedMaterial/edit"; // Retorna la vista processedMaterial/edit.html
    }

    @PostMapping("/update")
    public String updateProcessedMaterial(@ModelAttribute ProcessedMaterial processedMaterial, Model model) {
        processedMaterialService.newProcessedMaterial(processedMaterial);
        return "redirect:/processedMaterial"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteProcessedMaterial(@PathVariable("id") Long id) {
        processedMaterialService.deleteProcessedMaterial(id);
        return "redirect:/processedMaterial"; // Redirige a la lista de clientes
    }
}
