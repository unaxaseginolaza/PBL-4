// CustomerController.java
package com.example.pbl4.customer;

import com.example.pbl4.config.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/customer/")
public class CustomerController {

    private final CustomerService customerService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomerController(CustomerService customerService, BCryptPasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/list")
    public String getCustomers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "customer/customer_list"; // Retorna la vista customer/list.html
    }

    @PostMapping("/create")
    public String createCustomer(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("rol") Rol rol,
                                 @RequestParam("companyName") String companyName,
                                 Model model) {

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(passwordEncoder.encode(password));
        customer.setRol(rol);
        customer.setCompanyName(companyName);

        customerService.newCustomer(customer);
        return "redirect:/customer/list"; // Redirige a la lista de clientes
    }

    @GetMapping("/edit/{id}")
    public String updateCustomerForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findCustomerById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("customer", customer);
        model.addAttribute("roles", Rol.values()); // Para rellenar el select
        return "customer/customer_form"; // Retorna la vista customer/edit.html
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customer/list"; // Redirige a la lista de clientes
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/list"; // Redirige a la lista de clientes
    }

    @GetMapping("/form")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("roles", Rol.values()); // Para rellenar el select
        return "customer/customer_form";
    }
}