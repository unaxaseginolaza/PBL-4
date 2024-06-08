package com.example.pbl4.employee;

import com.example.pbl4.config.Rol;
import com.example.pbl4.section.Section;
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

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Employee(Long id, String username, String password, Rol rol , String name, String surname, Employee manager, Section section) {
        super(id, username, password, rol);
        this.name = name;
        this.surname = surname;
        this.manager = manager;
        this.section = section;
    }

    public Employee(String name, String surname, Employee manager, Section section) {
        this.name = name;
        this.surname = surname;
        this.manager = manager;
        this.section = section;
    }

    public Employee(String username, String password, Rol rol, String name, String surname, Employee manager, Section section) {
        super(username, password, rol);
        this.name = name;
        this.surname = surname;
        this.manager = manager;
        this.section = section;
    }
}


