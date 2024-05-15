package com.example.pbl4.employee;

import com.example.pbl4.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {
    private final EmployeeService EmployeeService;

    @Autowired
    public EmployeeController(EmployeeService EmployeeService) {
        this.EmployeeService = EmployeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return EmployeeService.getEmployees();
    }

    @PostMapping
    public ResponseEntity<Object> createEmployee(@RequestBody Employee Employee) {
        return this.EmployeeService.newEmployee(Employee);
    }

    @PutMapping
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee Employee) {
        return this.EmployeeService.newEmployee(Employee);
    }

    @DeleteMapping(path = "/{EmployeeId}/delete")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("EmployeeId")  Long id) {
        return this.EmployeeService.deleteEmployee(id);
    }
}
