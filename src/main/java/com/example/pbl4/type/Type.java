package com.example.pbl4.type;

import com.example.pbl4.preProcessedMaterial.PreProcessedMaterial;
import jakarta.persistence.*;

@Entity
@Table
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // Relación OneToOne con PreProcessedMaterial
    @OneToOne(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private PreProcessedMaterial preProcessedMaterial;

    public Type(String description, String name, PreProcessedMaterial preProcessedMaterial) {
        this.description = description;
        this.name = name;
        this.preProcessedMaterial = preProcessedMaterial;
    }

    public Type(Long id, String description, String name, PreProcessedMaterial preProcessedMaterial) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.preProcessedMaterial = preProcessedMaterial;
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

    public PreProcessedMaterial getPreProcessedMaterial() {
        return preProcessedMaterial;
    }

    public void setPreProcessedMaterial(PreProcessedMaterial preProcessedMaterial) {
        this.preProcessedMaterial = preProcessedMaterial;
    }
}
