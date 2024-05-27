package com.example.pbl4.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "customer/list"; // Retorna la vista customer/list.html
    }

    @GetMapping("/new")
    public String createCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/new"; // Retorna la vista customer/new.html
    }

    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.newCustomer(customer);
        return "redirect:/customer"; // Redirige a la lista de clientes
    }

    @GetMapping("/edit/{id}")
    public String updateCustomerForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findCustomerById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("customer", customer);
        return "customer/edit"; // Retorna la vista customer/edit.html
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.newCustomer(customer);
        return "redirect:/customer"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer"; // Redirige a la lista de clientes
    }
}
