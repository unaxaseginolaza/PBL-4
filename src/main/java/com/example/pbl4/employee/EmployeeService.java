package com.example.pbl4.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Employee not found"));
    }

    public void newEmployee(Employee employee) {
        Optional<Employee> res = employeeRepository.findEmployeeByName(employee.getName());
        if (res.isPresent() && employee.getId() == null) {
            throw new IllegalStateException("Ya existe un empleado con name");
        }
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un Employee con esa id");
        }
        employeeRepository.deleteById(id);
    }
}
