package com.example.pbl4.preProcessedMaterial;


import com.example.pbl4.type.Type;
import jakarta.persistence.*;

@Entity
@Table
public class PreProcessedMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float quantity;

    @ManyToOne
    @JoinColumn(name = "material_type_id", nullable = false)
    private Type type;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public PreProcessedMaterial(Long id, float quantity, Type type) {
        this.id = id;
        this.quantity = quantity;
        this.type = type;
    }

    public PreProcessedMaterial() {
    }

    public PreProcessedMaterial(Type type, float quantity) {
        this.type = type;
        this.quantity = quantity;
    }
}
