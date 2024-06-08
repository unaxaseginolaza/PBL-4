package com.example.pbl4.preProcessedMaterial;

import com.example.pbl4.type.Type;
import com.example.pbl4.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping (path = "preProcessedMaterial")
public class PreProcessedMaterialController {

    private final PreProcessedMaterialService preProcessedMaterialService;
    private final TypeService typeService;

    @Autowired
    public PreProcessedMaterialController(PreProcessedMaterialService preProcessedMaterialService, TypeService typeService) {
        this.preProcessedMaterialService = preProcessedMaterialService;
        this.typeService = typeService;
    }

    @GetMapping("/list")
    public String getPreProcessedMaterials(Model model) {
        List<PreProcessedMaterial> preProcessedMaterials = preProcessedMaterialService.getPreProcessedMaterials();
        model.addAttribute("preProcessedMaterials", preProcessedMaterials);
        return "preProcessedMaterial/preProcessedMaterial_list"; // Retorna la vista preProcessedMaterial/list.html
    }

    
    @PostMapping("/create")
    public String createPreProcessedMaterial(@RequestParam("quantity") Float quantity,
                                 @RequestParam(value = "type", required = true) Long typeId,
                                 Model model) {
        // Crear el nuevo preProcessedMaterial
        PreProcessedMaterial preProcessedMaterial = new PreProcessedMaterial();
        preProcessedMaterial.setQuantity(quantity);

        // Asignar el type, siempre se tiene que proporcionar
        if (typeId != null) {
            Type type = typeService.findTypeById(typeId);
            preProcessedMaterial.setType(type);
        }

        // Guardar el nuevo preProcessedMaterial
        preProcessedMaterialService.newPreProcessedMaterial(preProcessedMaterial);
        return "redirect:/preProcessedMaterial/list"; // Redirige a la lista de preProcessedMaterial
    }

    @GetMapping("/edit/{id}")
    public String updatePreProcessedMaterialForm(@PathVariable("id") Long id, Model model) {
        PreProcessedMaterial preProcessedMaterial = preProcessedMaterialService.findPreProcessedMaterialById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("preProcessedMaterial", preProcessedMaterial);
        return "preProcessedMaterial/preProcessedMaterial_form"; // Retorna la vista preProcessedMaterial/edit.html
    }

    @PostMapping("/update")
    public String updatePreProcessedMaterial(@ModelAttribute PreProcessedMaterial preProcessedMaterial, Model model) {
        preProcessedMaterialService.updatePreProcessedMaterial(preProcessedMaterial);
        return "redirect:/preProcessedMaterial/list"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deletePreProcessedMaterial(@PathVariable("id") Long id) {
        preProcessedMaterialService.deletePreProcessedMaterial(id);
        return "redirect:/preProcessedMaterial/list"; // Redirige a la lista de clientes
    }

    @GetMapping("/form")
    public String showPreProcessedMaterialForm(Model model) {
        model.addAttribute("preProcessedMaterial", new PreProcessedMaterial());
        model.addAttribute("types", typeService.getAllTypes());
        return "preProcessedMaterial/preProcessedMaterial_form";
    }
}
