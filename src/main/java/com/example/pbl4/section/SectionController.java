package com.example.pbl4.section;

import com.example.pbl4.section.Section;
import com.example.pbl4.section.SectionService;
import com.example.pbl4.section.Section;
import com.example.pbl4.section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "section")
public class SectionController {
    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping
    public String getSections(Model model) {
        List<Section> sections = sectionService.getSections();
        model.addAttribute("sections", sections);
        return "section/list"; // Retorna la vista section/list.html
    }

    @GetMapping("/new")
    public String createSectionForm(Model model) {
        model.addAttribute("section", new Section());
        return "section/new"; // Retorna la vista section/new.html
    }

    @PostMapping
    public String createSection(@ModelAttribute Section section, Model model) {
        sectionService.newSection(section);
        return "redirect:/section"; // Redirige a la lista de clientes
    }

    @GetMapping("/edit/{id}")
    public String updateSectionForm(@PathVariable("id") Long id, Model model) {
        Section section = sectionService.findSectionById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("section", section);
        return "section/edit"; // Retorna la vista section/edit.html
    }

    @PostMapping("/update")
    public String updateSection(@ModelAttribute Section section, Model model) {
        sectionService.newSection(section);
        return "redirect:/section"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteSection(@PathVariable("id") Long id) {
        sectionService.deleteSection(id);
        return "redirect:/section"; // Redirige a la lista de clientes
    }
}
