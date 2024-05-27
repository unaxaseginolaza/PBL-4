package com.example.pbl4.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public String getSuppliers(Model model) {
        List<Supplier> suppliers = supplierService.getSuppliers();
        model.addAttribute("suppliers", suppliers);
        return "supplier/list"; // Retorna la vista supplier/list.html
    }

    @GetMapping("/new")
    public String createSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier/new"; // Retorna la vista supplier/new.html
    }

    @PostMapping
    public String createSupplier(@ModelAttribute Supplier supplier, Model model) {
        supplierService.newSupplier(supplier);
        return "redirect:/supplier"; // Redirige a la lista de clientes
    }

    @GetMapping("/edit/{id}")
    public String updateSupplierForm(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.findSupplierById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("supplier", supplier);
        return "supplier/edit"; // Retorna la vista supplier/edit.html
    }

    @PostMapping("/update")
    public String updateSupplier(@ModelAttribute Supplier supplier, Model model) {
        supplierService.newSupplier(supplier);
        return "redirect:/supplier"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id) {
        supplierService.deleteSupplier(id);
        return "redirect:/supplier"; // Redirige a la lista de clientes
    }
}
