package com.example.pbl4.doTask;

import com.example.pbl4.doTask.DoTask;
import com.example.pbl4.doTask.DoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/doTask")
public class DoTaskController {
    private final DoTaskService doTaskService;

    @Autowired
    public DoTaskController(DoTaskService doTaskService) {
        this.doTaskService = doTaskService;
    }

    @GetMapping
    public List<DoTask> getDoTasks() {
        return doTaskService.getDoTasks();
    }

    @PostMapping
    public ResponseEntity<Object> createDoTask(@RequestBody DoTask doTask) {
        return this.doTaskService.newDoTask(doTask);
    }

    @PutMapping
    public ResponseEntity<Object> updateDoTask(@RequestBody DoTask doTask) {
        return this.doTaskService.newDoTask(doTask);
    }

    @DeleteMapping(path = "/{DoTaskId}/delete")
    public ResponseEntity<Object> deleteDoTask(@PathVariable("DoTaskId") Long id) {
        return this.doTaskService.deleteDoTask(id);
    }
}
