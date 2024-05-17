package com.example.pbl4.task;


import com.example.pbl4.employee.Employee;
import com.example.pbl4.preProcessedMaterial.PreProcessedMaterial;
import com.example.pbl4.processedMaterial.ProcessedMaterial;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1024)
    private String summary;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Employee creator;

    @ManyToOne
    @JoinColumn(name = "inCharge_id")
    private Employee inCharge;

    @ManyToOne
    @JoinColumn(name = "preProcessedMaterial_id")
    private PreProcessedMaterial preProcessedMaterial;

    @ManyToOne
    @JoinColumn(name = "processedMaterial_id")
    private ProcessedMaterial processedMaterial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Employee getCreator() {
        return creator;
    }

    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    public Employee getInCharge() {
        return inCharge;
    }

    public void setInCharge(Employee inCharge) {
        this.inCharge = inCharge;
    }

    public PreProcessedMaterial getPreProcessedMaterial() {
        return preProcessedMaterial;
    }

    public void setPreProcessedMaterial(PreProcessedMaterial preProcessedMaterial) {
        this.preProcessedMaterial = preProcessedMaterial;
    }

    public ProcessedMaterial getProcessedMaterial() {
        return processedMaterial;
    }

    public void setProcessedMaterial(ProcessedMaterial processedMaterial) {
        this.processedMaterial = processedMaterial;
    }

    public Task() {
    }

    public Task(Long id, String title, String summary, Employee creator, Employee inCharge, PreProcessedMaterial preProcessedMaterial, ProcessedMaterial processedMaterial) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.creator = creator;
        this.inCharge = inCharge;
        this.preProcessedMaterial = preProcessedMaterial;
        this.processedMaterial = processedMaterial;
    }

    public Task(String title, String summary, Employee creator, Employee inCharge, PreProcessedMaterial preProcessedMaterial, ProcessedMaterial processedMaterial) {
        this.title = title;
        this.summary = summary;
        this.creator = creator;
        this.inCharge = inCharge;
        this.preProcessedMaterial = preProcessedMaterial;
        this.processedMaterial = processedMaterial;
    }
}

