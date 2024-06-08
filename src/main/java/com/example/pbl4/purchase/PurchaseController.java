package com.example.pbl4.purchase;

import com.example.pbl4.preProcessedMaterial.PreProcessedMaterial;
import com.example.pbl4.preProcessedMaterial.PreProcessedMaterialService;
import com.example.pbl4.purchase.Purchase;
import com.example.pbl4.purchase.Purchase;
import com.example.pbl4.purchase.PurchaseService;
import com.example.pbl4.purchase.PurchaseService;
import com.example.pbl4.section.Section;
import com.example.pbl4.supplier.Supplier;
import com.example.pbl4.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final SupplierService supplierService;
    private final PreProcessedMaterialService preProcessedMaterialService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, SupplierService supplierService, PreProcessedMaterialService preProcessedMaterialService) {
        this.purchaseService = purchaseService;
        this.supplierService = supplierService;
        this.preProcessedMaterialService = preProcessedMaterialService;
    }

    @GetMapping("/list")
    public String getPurchases(Model model) {
        List<Purchase> purchases = purchaseService.getPurchases();
        model.addAttribute("purchases", purchases);
        return "purchase/purchase_list"; // Retorna la vista purchase/list.html
    }

    @PostMapping("/create")
    public String createPurchase(@RequestParam("price") Float price,
                                 @RequestParam("quantity") Float quantity,
                                 @RequestParam("date") LocalDate date,
                                 @RequestParam("supplier") Long supplierId,
                                 @RequestParam("preProcessedMaterial") Long preProcessedMaterialId,
                                 Model model) {

        // Crear el nuevo empleado
        Purchase purchase = new Purchase();
        purchase.setPrice(price);
        purchase.setQuantity(quantity);
        purchase.setDate(date);

        // Asignar el vendedor si se proporciona
        if (supplierId != null) {
            Supplier supplier = supplierService.findSupplierById(supplierId);
            purchase.setSupplier(supplier);
        }

        // Asignar el vendedor si se proporciona
        if (supplierId != null) {
            PreProcessedMaterial preProcessedMaterial = preProcessedMaterialService.findPreProcessedMaterialById(preProcessedMaterialId);
            purchase.setPreProcessedMaterial(preProcessedMaterial);
            preProcessedMaterial.setQuantity(preProcessedMaterial.getQuantity()+quantity);
            preProcessedMaterialService.updatePreProcessedMaterial(preProcessedMaterial);
        }

        // Guardar el nuevo empleado
        purchaseService.newPurchase(purchase);
        return "redirect:/purchase/list"; // Redirige a la lista de empleados
    }

    /*@GetMapping("/edit/{id}")
    public String updatePurchaseForm(@PathVariable("id") Long id, Model model) {
        Purchase purchase = purchaseService.findPurchaseById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("purchase", purchase);
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        model.addAttribute("preProcessedMaterials", preProcessedMaterialService.getPreProcessedMaterials());
        return "purchase/purchase_form"; // Retorna la vista purchase/edit.html
    }

    @PostMapping("/update")
    public String updatePurchase(@ModelAttribute Purchase purchase, Model model) {
        purchaseService.newPurchase(purchase);
        return "redirect:/purchase/list"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deletePurchase(@PathVariable("id") Long id) {
        purchaseService.deletePurchase(id);
        return "redirect:/purchase/list"; // Redirige a la lista de clientes
    }*/

    @GetMapping("/form")
    public String showPurchaseForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        model.addAttribute("preProcessedMaterials", preProcessedMaterialService.getPreProcessedMaterials());
        return "purchase/purchase_form";
    }
}
