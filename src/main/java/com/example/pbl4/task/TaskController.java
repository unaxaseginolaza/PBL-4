package com.example.pbl4.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "task/list"; // Retorna la vista task/list.html
    }

    @GetMapping("/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task/new"; // Retorna la vista task/new.html
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task, Model model) {
        taskService.newTask(task);
        return "redirect:/task"; // Redirige a la lista de clientes
    }

    @GetMapping("/edit/{id}")
    public String updateTaskForm(@PathVariable("id") Long id, Model model) {
        Task task = taskService.findTaskById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("task", task);
        return "task/edit"; // Retorna la vista task/edit.html
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task, Model model) {
        taskService.newTask(task);
        return "redirect:/task"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/task"; // Redirige a la lista de clientes
    }
}
