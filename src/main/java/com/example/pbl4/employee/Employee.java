package com.example.pbl4.employee;

import com.example.pbl4.user.User;
import jakarta.persistence.*;

@Entity
@Table
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {

    private String name;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    public Employee(Long id, String username, String password, Integer permisos, String name, String surname) {
        super(id, username, password, permisos);
        this.name = name;
        this.surname = surname;
    }

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Employee(String username, String password, Integer permisos, String name, String surname) {
        super(username, password, permisos);
        this.name = name;
        this.surname = surname;
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}


