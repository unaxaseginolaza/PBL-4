package com.example.pbl4.task;


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

    public Task() {
    }

    public Task(String title, String summary) {
        this.title = title;
        this.summary = summary;
    }

    public Task(Long id, String title, String summary) {
        this.id = id;
        this.title = title;
        this.summary = summary;
    }
}

