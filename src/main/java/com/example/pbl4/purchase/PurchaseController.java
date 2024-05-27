package com.example.pbl4.purchase;

import com.example.pbl4.purchase.Purchase;
import com.example.pbl4.purchase.PurchaseService;
import com.example.pbl4.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public String getPurchases(Model model) {
        List<Purchase> purchases = purchaseService.getPurchases();
        model.addAttribute("purchases", purchases);
        return "purchase/list"; // Retorna la vista purchase/list.html
    }

    @GetMapping("/new")
    public String createPurchaseForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        return "purchase/new"; // Retorna la vista purchase/new.html
    }

    @PostMapping
    public String createPurchase(@ModelAttribute Purchase purchase, Model model) {
        purchaseService.newPurchase(purchase);
        return "redirect:/purchase"; // Redirige a la lista de clientes
    }

    @GetMapping("/edit/{id}")
    public String updatePurchaseForm(@PathVariable("id") Long id, Model model) {
        Purchase purchase = purchaseService.findPurchaseById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("purchase", purchase);
        return "purchase/edit"; // Retorna la vista purchase/edit.html
    }

    @PostMapping("/update")
    public String updatePurchase(@ModelAttribute Purchase purchase, Model model) {
        purchaseService.newPurchase(purchase);
        return "redirect:/purchase"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deletePurchase(@PathVariable("id") Long id) {
        purchaseService.deletePurchase(id);
        return "redirect:/purchase"; // Redirige a la lista de clientes
    }
}
