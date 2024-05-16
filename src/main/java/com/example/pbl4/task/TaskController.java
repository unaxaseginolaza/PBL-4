package com.example.pbl4.task;

import com.example.pbl4.task.Task;
import com.example.pbl4.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody Task task) {
        return this.taskService.newTask(task);
    }

    @PutMapping
    public ResponseEntity<Object> updateTask(@RequestBody Task task) {
        return this.taskService.newTask(task);
    }

    @DeleteMapping(path = "/{TaskId}/delete")
    public ResponseEntity<Object> deleteTask(@PathVariable("TaskId") Long id) {
        return this.taskService.deleteTask(id);
    }
}
