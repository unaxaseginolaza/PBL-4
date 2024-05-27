package com.example.pbl4.section;

import com.example.pbl4.employee.Employee;
import com.example.pbl4.preProcessedMaterial.PreProcessedMaterial;
import jakarta.persistence.*;

@Entity
@Table
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "bossEmployee_id")
    private Employee bossEmployee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getBossEmployee() {
        return bossEmployee;
    }

    public void setBossEmployee(Employee bossEmployee) {
        this.bossEmployee = bossEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Section(Long id, String name, Employee bossEmployee) {
        this.id = id;
        this.name = name;
        this.bossEmployee = bossEmployee;
    }

    public Section() {
    }

    public Section(String name, Employee bossEmployee) {
        this.name = name;
        this.bossEmployee = bossEmployee;
    }
}
