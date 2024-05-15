package com.example.pbl4.customer;

import com.example.pbl4.customer.Customer;
import com.example.pbl4.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {
    
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        return this.customerService.newCustomer(customer);
    }

    @PutMapping
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer) {
        return this.customerService.newCustomer(customer);
    }

    @DeleteMapping(path = "/{CustomerId}/delete")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("CustomerId") Long id) {
        return this.customerService.deleteCustomer(id);
    }

}
