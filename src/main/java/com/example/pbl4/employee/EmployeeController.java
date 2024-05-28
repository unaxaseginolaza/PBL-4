package com.example.pbl4.employee;

import com.example.pbl4.employee.Employee;
import com.example.pbl4.employee.EmployeeService;
import com.example.pbl4.employee.EmployeeService;
import com.example.pbl4.section.Section;
import com.example.pbl4.section.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public EmployeeController(EmployeeService employeeService, SectionRepository sectionRepository, BCryptPasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.sectionRepository = sectionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employee/list"; // Retorna la vista employee/list.html
    }

    /*@GetMapping("/new")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/new"; // Retorna la vista employee/new.html
    }*/

    @PostMapping("/create")
    public String createEmployee(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("permisos") Integer permisos,
                                 @RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("manager") Long managerId,
                                 @RequestParam("section") Long sectionId,
                                 Model model) {
        // Crear el nuevo empleado
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword(passwordEncoder.encode(password));
        employee.setPermisos(permisos);
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
        return "redirect:/employee"; // Redirige a la lista de empleados
    }

    @GetMapping("/edit/{id}")
    public String updateEmployeeForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("employee", employee);
        return "employee/edit"; // Retorna la vista employee/edit.html
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee, Model model) {
        employeeService.newEmployee(employee);
        return "redirect:/employee"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee"; // Redirige a la lista de clientes
    }

    @GetMapping("/form")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_form";
    }
}
