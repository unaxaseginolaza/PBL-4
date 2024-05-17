package com.example.pbl4.employee;

import com.example.pbl4.employee.Employee;
import com.example.pbl4.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    HashMap<String, Object> datos;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }

    public ResponseEntity<Object> newEmployee(Employee employee) {
        Optional<Employee> res = employeeRepository.findEmployeeByName(employee.getName());
        datos = new HashMap<>();

        if (res.isPresent() && employee.getId()==null) {
            //throw new IllegalStateException("ya existe el producto");
            datos.put("error", true);
            datos.put("message", "Ya existe un Employee con ese nombre");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        if (employee.getId()!= null) {
            datos.put("message", "Se actualizo con exito");
        }
        employeeRepository.save(employee);
        datos.put("data", employee);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteEmployee(Long id) {
        boolean exists = this.employeeRepository.existsById(id);
        datos = new HashMap<>();

        if (!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un Employee con esa id");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        employeeRepository.deleteById(id);
        datos.put("message", "Employee eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
