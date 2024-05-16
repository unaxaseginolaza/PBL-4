package com.example.pbl4.doTask;


import com.example.pbl4.employee.Employee;
import com.example.pbl4.task.Task;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table
public class DoTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startingDateTime;
    private LocalDateTime finishingDateTime;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartingDateTime() {
        return startingDateTime;
    }

    public void setStartingDateTime(LocalDateTime startingDateTime) {
        this.startingDateTime = startingDateTime;
    }

    public LocalDateTime getFinishingDateTime() {
        return finishingDateTime;
    }

    public void setFinishingDateTime(LocalDateTime finishingDateTime) {
        this.finishingDateTime = finishingDateTime;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public DoTask(LocalDateTime startingDateTime, LocalDateTime finishingDateTime, Task task, Employee employee) {
        this.startingDateTime = startingDateTime;
        this.finishingDateTime = finishingDateTime;
        this.task = task;
        this.employee = employee;
    }

    public DoTask(){

    }

    public DoTask(Long id, LocalDateTime startingDateTime, Task task, LocalDateTime finishingDateTime, Employee employee) {
        this.id = id;
        this.startingDateTime = startingDateTime;
        this.task = task;
        this.finishingDateTime = finishingDateTime;
        this.employee = employee;
    }


}
