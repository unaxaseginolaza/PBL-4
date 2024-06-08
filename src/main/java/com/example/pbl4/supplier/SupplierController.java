package com.example.pbl4.supplier;

import com.example.pbl4.supplier.Supplier;
import com.example.pbl4.section.Section;
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

    @GetMapping("/list")
    public String getSuppliers(Model model) {
        List<Supplier> suppliers = supplierService.getSuppliers();
        model.addAttribute("suppliers", suppliers);
        return "supplier/supplier_list"; // Retorna la vista supplier/list.html
    }
    @PostMapping("/create")
    public String createSupplier(@RequestParam("direction") String direction,
                                 @RequestParam("name") String name,

                                 Model model) {

        // Crear el nuevo supplier
        Supplier supplier = new Supplier();
        supplier.setDirection(direction);
        supplier.setName(name);


        // Guardar el nuevo supplier
        supplierService.newSupplier(supplier);
        return "redirect:/supplier/list"; // Redirige a la lista de empleados
    }

    @GetMapping("/edit/{id}")
    public String updateSupplierForm(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.findSupplierById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("supplier", supplier);
        return "supplier/supplier_form"; // Retorna la vista supplier/supplier_form.html
    }

    @PostMapping("/update")
    public String updateSupplier(@ModelAttribute Supplier supplier, Model model) {
        supplierService.updateSupplier(supplier);
        return "redirect:/supplier/list"; // Redirige a la lista de proveedores
    }

    @PostMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id) {
        supplierService.deleteSupplier(id);
        return "redirect:/supplier/list"; // Redirige a la lista de proveedores
    }

    @GetMapping("/form")
    public String showSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier/supplier_form";
    }
}
