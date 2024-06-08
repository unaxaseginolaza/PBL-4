package com.example.pbl4.supplier;

import jakarta.persistence.*;

@Entity
@Table
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String direction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier() {
    }

    public Supplier(Long id, String name, String direction) {
        this.id = id;
        this.name = name;
        this.direction = direction;
    }

    public Supplier(String name, String direction) {
        this.name = name;
        this.direction = direction;
    }
}
