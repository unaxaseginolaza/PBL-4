package com.example.pbl4.purchase;


import com.example.pbl4.preProcessedMaterial.PreProcessedMaterial;
import com.example.pbl4.supplier.Supplier;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float price;
    private Float quantity;

    private LocalDate date;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private PreProcessedMaterial preProcessedMaterial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public PreProcessedMaterial getPreProcessedMaterial() {
        return preProcessedMaterial;
    }

    public void setPreProcessedMaterial(PreProcessedMaterial preProcessedMaterial) {
        this.preProcessedMaterial = preProcessedMaterial;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Purchase() {
    }

    public Purchase(Long id, Float price, LocalDate date, Float quantity, Supplier supplier, PreProcessedMaterial preProcessedMaterial) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.quantity = quantity;
        this.supplier = supplier;
        this.preProcessedMaterial = preProcessedMaterial;
    }

    public Purchase(Float price, Float quantity, LocalDate date, Supplier supplier, PreProcessedMaterial preProcessedMaterial) {
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.supplier = supplier;
        this.preProcessedMaterial = preProcessedMaterial;
    }
}
