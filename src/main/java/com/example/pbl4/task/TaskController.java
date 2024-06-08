package com.example.pbl4.task;

import com.example.pbl4.employee.Employee;
import com.example.pbl4.employee.EmployeeService;
import com.example.pbl4.preProcessedMaterial.PreProcessedMaterial;
import com.example.pbl4.preProcessedMaterial.PreProcessedMaterialService;
import com.example.pbl4.processedMaterial.ProcessedMaterial;
import com.example.pbl4.processedMaterial.ProcessedMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "task")
public class TaskController {
    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final PreProcessedMaterialService preProcessedMaterialService;
    private final ProcessedMaterialService processedMaterialService;

    @Autowired
    public TaskController(TaskService taskService, EmployeeService employeeService, PreProcessedMaterialService preProcessedMaterialService, ProcessedMaterialService processedMaterialService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.preProcessedMaterialService = preProcessedMaterialService;
        this.processedMaterialService = processedMaterialService;
    }

    @GetMapping("/list")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "task/task_list"; // Retorna la vista task/list.html
    }

    @PostMapping("/create")
    public String createTask(@RequestParam("title") String title,
                                 @RequestParam("summary") String summary,
                                 @RequestParam("creator") Long creatorId,
                                 @RequestParam("inCharge") Long inChargeId,
                                 @RequestParam(value = "preProcessedMaterial", required = false) Long preProcessedMaterialId,
                                 @RequestParam(value = "preProcessedMaterial", required = false) Long processedMaterialId,
                                 Model model) {

        // Crear el nuevo task
        Task task = new Task();
        task.setTitle(title);
        task.setSummary(summary);

        // Asignar el creator si se proporciona
        if (creatorId != null) {
            Employee creator = employeeService.findEmployeeById(creatorId);
            task.setCreator(creator);
        }

        // Asignar el employee encargado si se proporciona
        if (inChargeId != null) {
            Employee inCharge = employeeService.findEmployeeById(inChargeId);
            task.setInCharge(inCharge);
        }

        // Asignar el material sin proporcionar si no null
        if (preProcessedMaterialId != null) {
            PreProcessedMaterial preProcessedMaterial = preProcessedMaterialService.findPreProcessedMaterialById(preProcessedMaterialId);
            task.setPreProcessedMaterial(preProcessedMaterial);
        }

        // Asignar la sección si se proporciona
        if (processedMaterialId != null) {
            ProcessedMaterial processedMaterial = processedMaterialService.findProcessedMaterialById(processedMaterialId);
            task.setProcessedMaterial(processedMaterial);
        }

        // Guardar el nuevo tareas
        taskService.newTask(task);
        return "redirect:/task/list"; // Redirige a la lista de tareas
    }

    @GetMapping("/edit/{id}")
    public String updateTaskForm(@PathVariable("id") Long id, Model model) {
        Task task = taskService.findTaskById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("task", task);
        model.addAttribute("creators", employeeService.getAllEmployees());
        model.addAttribute("inCharges", employeeService.getAllEmployees());
        model.addAttribute("preProcessedMaterials", preProcessedMaterialService.getPreProcessedMaterials());
        model.addAttribute("processedMaterials", processedMaterialService.getProcessedMaterials());

        return "task/task_form"; // Retorna la vista task/edit.html
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task, Model model) {
        taskService.newTask(task);
        return "redirect:/task/list"; // Redirige a la lista de tareas
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/task/list"; // Redirige a la lista de tareas
    }

    @GetMapping("/form")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("creators", employeeService.getAllEmployees());
        model.addAttribute("inCharges", employeeService.getAllEmployees());
        model.addAttribute("preProcessedMaterials", preProcessedMaterialService.getPreProcessedMaterials());
        model.addAttribute("processedMaterials", processedMaterialService.getProcessedMaterials());

        return "task/task_form";// Ensena el formulario de tareas
    }
}
