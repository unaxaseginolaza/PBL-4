package com.example.pbl4.section;

import com.example.pbl4.employee.Employee;
import com.example.pbl4.employee.EmployeeService;
import com.example.pbl4.section.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "section")
public class SectionController {
    private final SectionService sectionService;
    private final EmployeeService employeeService;

    @Autowired
    public SectionController(SectionService sectionService, EmployeeService employeeService) {
        this.sectionService = sectionService;
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getSections(Model model) {
        List<Section> sections = sectionService.getSections();
        model.addAttribute("sections", sections);
        return "section/section_list"; // Retorna la vista section/section_list.html
    }

    @PostMapping("/create")
    public String createSection(@RequestParam("name") String name,
                                @RequestParam("bossEmployee") Long bossEmployeeId,
                                Model model) {

        //crear sección
        Section section = new Section();
        section.setName(name);

        // Asignar el empleado jefe de la seccion, siempre se proporciona
        if (bossEmployeeId != null) {
            Employee bossEmployee = employeeService.findEmployeeById(bossEmployeeId);
            section.setBossEmployee(bossEmployee);
        }

        // Guardar la nueva sección
        sectionService.newSection(section);
        return "redirect:/section/list"; // Redirige a la lista de empleados
    }

    @GetMapping("/edit/{id}")
    public String updateSectionForm(@PathVariable("id") Long id, Model model) {
        Section section = sectionService.findSectionById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("section", section);
        model.addAttribute("bossEmployees", employeeService.getAllEmployees());
        return "section/section_form"; // Retorna la vista section/edit.html
    }

    @PostMapping("/update")
    public String updateSection(@ModelAttribute Section section, Model model) {
        sectionService.updateSection(section);
        return "redirect:/section/list"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteSection(@PathVariable("id") Long id) {
        sectionService.deleteSection(id);
        return "redirect:/section/list"; // Redirige a la lista de clientes
    }

    @GetMapping("/form")
    public String showSectionForm(Model model) {
        model.addAttribute("section", new Section());
        model.addAttribute("bossEmployees", employeeService.getAllEmployees());
        return "section/section_form";
    }
}
