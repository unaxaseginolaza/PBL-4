package com.example.pbl4.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "sale")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public String getSales(Model model) {
        List<Sale> sales = saleService.getSales();
        model.addAttribute("sales", sales);
        return "sale/sale_list"; // Retorna la vista sale/list.html
    }

    @GetMapping("/new")
    public String createSaleForm(Model model) {
        model.addAttribute("sale", new Sale());
        return "sale/new"; // Retorna la vista sale/new.html
    }

    @PostMapping
    public String createSale(@ModelAttribute Sale sale, Model model) {
        saleService.newSale(sale);
        return "redirect:/sale"; // Redirige a la lista de clientes
    }

    /*@GetMapping("/edit/{id}")
    public String updateSaleForm(@PathVariable("id") Long id, Model model) {
        Sale sale = saleService.findSaleById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("sale", sale);
        return "sale/edit"; // Retorna la vista sale/edit.html
    }*/

    @PostMapping("/update")
    public String updateSale(@ModelAttribute Sale sale, Model model) {
        saleService.newSale(sale);
        return "redirect:/sale"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteSale(@PathVariable("id") Long id) {
        saleService.deleteSale(id);
        return "redirect:/sale"; // Redirige a la lista de clientes
    }
}
