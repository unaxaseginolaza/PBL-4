package com.example.pbl4.processedMaterial;

import com.example.pbl4.preProcessedMaterial.PreProcessedMaterial;
import jakarta.persistence.*;


@Entity
@Table
public class ProcessedMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float quantity;

    private float precioKg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preProcessedMaterial_id")
    private PreProcessedMaterial preProcessedMaterial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public PreProcessedMaterial getPreProcessedMaterial() {
        return preProcessedMaterial;
    }

    public void setPreProcessedMaterial(PreProcessedMaterial preProcessedMaterial) {
        this.preProcessedMaterial = preProcessedMaterial;
    }

    public float getPrecioKg() {
        return precioKg;
    }

    public void setPrecioKg(float precioKg) {
        this.precioKg = precioKg;
    }

    public ProcessedMaterial() {
    }

    public ProcessedMaterial(Long id, float quantity, PreProcessedMaterial preProcessedMaterial, float precioKg) {
        this.id = id;
        this.quantity = quantity;
        this.preProcessedMaterial = preProcessedMaterial;
        this.precioKg = precioKg;
    }

    public ProcessedMaterial(float quantity, PreProcessedMaterial preProcessedMaterial, float precioKg) {
        this.quantity = quantity;
        this.preProcessedMaterial = preProcessedMaterial;
        this.precioKg = precioKg;
    }
}
