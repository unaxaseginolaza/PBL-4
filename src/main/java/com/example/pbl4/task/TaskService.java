package com.example.pbl4.task;

import com.example.pbl4.task.Task;
import com.example.pbl4.task.Task;
import com.example.pbl4.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return this.taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Task not found"));
    }

    public void newTask(Task task) {
        Optional<Task> res = taskRepository.findTaskByTitle(task.getTitle());
        if (res.isPresent() && task.getId() == null) {
            throw new IllegalStateException("Ya existe una tarea con ese título");
        }
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        boolean exists = taskRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un Task con esa id");
        }
        taskRepository.deleteById(id);
    }

    public void updateTask(Task updatedTask) {
        Task existingTask = findTaskById(updatedTask.getId());

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setSummary(updatedTask.getSummary());
        existingTask.setCreator(updatedTask.getCreator());
        existingTask.setInCharge(updatedTask.getInCharge());
        existingTask.setPreProcessedMaterial(updatedTask.getPreProcessedMaterial());
        existingTask.setProcessedMaterial(updatedTask.getProcessedMaterial());

        taskRepository.save(existingTask);
    }
}
