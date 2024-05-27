package com.example.pbl4.preProcessedMaterial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping (path = "preProcessedMaterial")
public class PreProcessedMaterialController {
    private final PreProcessedMaterialService preProcessedMaterialService;

    @Autowired
    public PreProcessedMaterialController(PreProcessedMaterialService preProcessedMaterialService) {
        this.preProcessedMaterialService = preProcessedMaterialService;
    }

    @GetMapping
    public String getPreProcessedMaterials(Model model) {
        List<PreProcessedMaterial> preProcessedMaterials = preProcessedMaterialService.getPreProcessedMaterials();
        model.addAttribute("preProcessedMaterials", preProcessedMaterials);
        return "preProcessedMaterial/list"; // Retorna la vista preProcessedMaterial/list.html
    }

    @GetMapping("/new")
    public String createPreProcessedMaterialForm(Model model) {
        model.addAttribute("preProcessedMaterial", new PreProcessedMaterial());
        return "preProcessedMaterial/new"; // Retorna la vista preProcessedMaterial/new.html
    }

    @PostMapping
    public String createPreProcessedMaterial(@ModelAttribute PreProcessedMaterial preProcessedMaterial, Model model) {
        preProcessedMaterialService.newPreProcessedMaterial(preProcessedMaterial);
        return "redirect:/preProcessedMaterial"; // Redirige a la lista de clientes
    }

    @GetMapping("/edit/{id}")
    public String updatePreProcessedMaterialForm(@PathVariable("id") Long id, Model model) {
        PreProcessedMaterial preProcessedMaterial = preProcessedMaterialService.findPreProcessedMaterialById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("preProcessedMaterial", preProcessedMaterial);
        return "preProcessedMaterial/edit"; // Retorna la vista preProcessedMaterial/edit.html
    }

    @PostMapping("/update")
    public String updatePreProcessedMaterial(@ModelAttribute PreProcessedMaterial preProcessedMaterial, Model model) {
        preProcessedMaterialService.newPreProcessedMaterial(preProcessedMaterial);
        return "redirect:/preProcessedMaterial"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deletePreProcessedMaterial(@PathVariable("id") Long id) {
        preProcessedMaterialService.deletePreProcessedMaterial(id);
        return "redirect:/preProcessedMaterial"; // Redirige a la lista de clientes
    }
}
