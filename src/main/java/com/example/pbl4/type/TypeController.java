package com.example.pbl4.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "type")
public class TypeController {
    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/list")
    public String getTypes(Model model) {
        List<Type> types = typeService.getTypes();
        model.addAttribute("types", types);
        return "type/type_list"; // Retorna la vista type/list.html
    }

    @PostMapping("/create")
    public String createType(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 Model model) {

        // Crear el nuevo tipo
        Type type = new Type();
        type.setName(name);
        type.setDescription(description);

        // Guardar el nuevo tipo
        typeService.newType(type);
        return "redirect:/type/list"; // Redirige a la lista de empleados
    }

    @GetMapping("/edit/{id}")
    public String updateTypeForm(@PathVariable("id") Long id, Model model) {
        Type type = typeService.findTypeById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("type", type);
        return "type/type_form"; // Retorna la vista type/edit.html
    }

    @PostMapping("/update")
    public String updateType(@ModelAttribute Type type, Model model) {
        typeService.newType(type);
        return "redirect:/type/list"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteType(@PathVariable("id") Long id) {
        typeService.deleteType(id);
        return "redirect:/type/list"; // Redirige a la lista de clientes
    }

    @GetMapping("/form")
    public String showTypeForm(Model model) {
        model.addAttribute("type", new Type());
        return "type/type_form";
    }
}
