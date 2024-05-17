package com.example.pbl4.employee;

import com.example.pbl4.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping
    public ResponseEntity<Object> createEmployee(@RequestBody Employee Employee) {
        return this.employeeService.newEmployee(Employee);
    }

    @PutMapping
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee Employee) {
        return this.employeeService.newEmployee(Employee);
    }

    @DeleteMapping(path = "/{EmployeeId}/delete")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("EmployeeId")  Long id) {
        return this.employeeService.deleteEmployee(id);
    }
}
