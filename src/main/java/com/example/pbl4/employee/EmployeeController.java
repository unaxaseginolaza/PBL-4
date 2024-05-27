package com.example.pbl4.employee;

import com.example.pbl4.employee.Employee;
import com.example.pbl4.employee.EmployeeService;
import com.example.pbl4.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employee/list"; // Retorna la vista employee/list.html
    }

    @GetMapping("/new")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/new"; // Retorna la vista employee/new.html
    }

    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee, Model model) {
        employeeService.newEmployee(employee);
        return "redirect:/employee"; // Redirige a la lista de clientes
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
}
