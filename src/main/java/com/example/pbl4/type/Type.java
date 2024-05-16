package com.example.pbl4.type;

import jakarta.persistence.*;

@Entity
@Table
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    public Type(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public Type(Long id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public Type(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
