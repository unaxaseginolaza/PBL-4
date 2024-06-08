// EmployeeController.java
package com.example.pbl4.employee;

import com.example.pbl4.config.Rol;
import com.example.pbl4.section.Section;
import com.example.pbl4.section.SectionRepository;
import com.example.pbl4.section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/employee/")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final SectionRepository sectionRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SectionService sectionService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, SectionRepository sectionRepository, BCryptPasswordEncoder passwordEncoder, SectionService sectionService) {
        this.employeeService = employeeService;
        this.sectionRepository = sectionRepository;
        this.passwordEncoder = passwordEncoder;
        this.sectionService = sectionService;
    }

    @GetMapping("/list")
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employee/employee_list"; // Retorna la vista employee/list.html
    }

    @PostMapping("/create")
    public String createEmployee(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("rol") Rol rol,
                                 @RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam(value = "manager", required = false) Long managerId,
                                 @RequestParam(value = "section", required = false) Long sectionId,
                                 Model model) {
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword(passwordEncoder.encode(password));
        employee.setRol(rol);
        employee.setName(name);
        employee.setSurname(surname);

        // Asignar el manager si se proporciona
        if (managerId != null) {
            Employee manager = employeeService.findEmployeeById(managerId);
            employee.setManager(manager);
        }

        // Asignar la sección si se proporciona
        if (sectionId != null) {
            Section section = sectionRepository.findById(sectionId).orElse(null);
            employee.setSection(section);
        }

        // Guardar el nuevo empleado
        employeeService.newEmployee(employee);
        return "redirect:/employee/list"; // Redirige a la lista de empleados
    }

    @GetMapping("/edit/{id}")
    public String updateEmployeeForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("employee", employee);
        model.addAttribute("managers", employeeService.getAllEmployees());
        model.addAttribute("sections", sectionService.getAllSections());
        model.addAttribute("roles", Rol.values()); // Para rellenar el select

        return "employee/employee_form"; // Retorna la vista employee_form.html por que el formulario es el mismo pero en uno jala los datos y en el otro los actualiza
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employee/list"; // Redirige a la lista de empleados
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee/list"; // Redirige a la lista de empleado
    }

    @GetMapping("/form")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("managers", employeeService.getAllEmployees());
        model.addAttribute("sections", sectionService.getAllSections());
        model.addAttribute("roles", Rol.values()); // Para rellenar el select
        return "employee/employee_form";
    }
}
