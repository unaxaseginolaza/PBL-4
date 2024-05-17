package com.example.pbl4.employee;

import com.example.pbl4.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //    @Query("SELECT * from Product p where p.name like '%1' ")
    Optional<Employee> findEmployeeByName(String name);
}
