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

    @GetMapping
    public String getTypes(Model model) {
        List<Type> types = typeService.getTypes();
        model.addAttribute("types", types);
        return "type/list"; // Retorna la vista type/list.html
    }

    @GetMapping("/new")
    public String createTypeForm(Model model) {
        model.addAttribute("type", new Type());
        return "type/new"; // Retorna la vista type/new.html
    }

    @PostMapping
    public String createType(@ModelAttribute Type type, Model model) {
        typeService.newType(type);
        return "redirect:/type"; // Redirige a la lista de clientes
    }

    @GetMapping("/edit/{id}")
    public String updateTypeForm(@PathVariable("id") Long id, Model model) {
        Type type = typeService.findTypeById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("type", type);
        return "type/edit"; // Retorna la vista type/edit.html
    }

    @PostMapping("/update")
    public String updateType(@ModelAttribute Type type, Model model) {
        typeService.newType(type);
        return "redirect:/type"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteType(@PathVariable("id") Long id) {
        typeService.deleteType(id);
        return "redirect:/type"; // Redirige a la lista de clientes
    }
}
