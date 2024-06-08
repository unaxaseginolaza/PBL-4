package com.example.pbl4.customer;

import com.example.pbl4.customer.Customer;
import com.example.pbl4.customer.CustomerRepository;
import com.example.pbl4.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Customer not found"));
    }

    public void newCustomer(Customer customer) {
        Optional<Customer> res = customerRepository.findCustomerByCompanyNameAndUsername(customer.getCompanyName(), customer.getUsername());
        if (res.isPresent() && customer.getId() == null) {
            throw new IllegalStateException("Ya existe un cliente con ese username y company name");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un Customer con esa id");
        }
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Customer updatedCustomer) {
        Customer existingCustomer = findCustomerById(updatedCustomer.getId());

        existingCustomer.setUsername(updatedCustomer.getUsername());
        existingCustomer.setPassword(updatedCustomer.getPassword()); // En este punto, la contraseña ya debería estar codificada
        existingCustomer.setRol(updatedCustomer.getRol());
        existingCustomer.setCompanyName(updatedCustomer.getCompanyName());

        customerRepository.save(existingCustomer);
    }
}
