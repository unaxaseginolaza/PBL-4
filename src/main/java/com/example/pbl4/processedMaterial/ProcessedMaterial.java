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

    @ManyToOne
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

    public ProcessedMaterial() {
    }

    public ProcessedMaterial(Long id, float quantity, PreProcessedMaterial preProcessedMaterial) {
        this.id = id;
        this.quantity = quantity;
        this.preProcessedMaterial = preProcessedMaterial;
    }

    public ProcessedMaterial(float quantity, PreProcessedMaterial preProcessedMaterial) {
        this.quantity = quantity;
        this.preProcessedMaterial = preProcessedMaterial;
    }
}
