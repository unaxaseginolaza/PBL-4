package com.example.pbl4.sale;

import com.example.pbl4.customer.Customer;
import com.example.pbl4.processedMaterial.ProcessedMaterial;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float price;
    private float quantity;

    private LocalDate date;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private ProcessedMaterial processedMaterial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ProcessedMaterial getProcessedMaterial() {
        return processedMaterial;
    }

    public void setProcessedMaterial(ProcessedMaterial processedMaterial) {
        this.processedMaterial = processedMaterial;
    }

    public Sale() {
    }

    public Sale(Float price, Float quantity, LocalDate date, Customer customer, ProcessedMaterial processedMaterial) {
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.customer = customer;
        this.processedMaterial = processedMaterial;
    }

    public Sale(Long id, Float price, Float quantity, LocalDate date, Customer customer, ProcessedMaterial processedMaterial) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.customer = customer;
        this.processedMaterial = processedMaterial;
    }
}
