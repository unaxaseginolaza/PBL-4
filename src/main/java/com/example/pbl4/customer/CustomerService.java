package com.example.pbl4.customer;

import com.example.pbl4.customer.Customer;
import com.example.pbl4.customer.CustomerRepository;
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
    HashMap<String, Object> datos;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }

    public ResponseEntity<Object> newCustomer(Customer customer) {
        Optional<Customer> res = customerRepository.findProductByCompanyName(customer.getUsername());
        datos = new HashMap<>();

        if (res.isPresent() && customer.getId() == null) {
            //throw new IllegalStateException("ya existe el producto");
            datos.put("error", true);
            datos.put("message", "Ya existe un producto con ese nombre");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        if (customer.getId() != null) {
            datos.put("message", "Se actualizo con exito");
        }
        customerRepository.save(customer);
        datos.put("data", customer);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteCustomer(Long id) {
        boolean exists = this.customerRepository.existsById(id);
        datos = new HashMap<>();

        if (!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un Customer con esa id");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        customerRepository.deleteById(id);
        datos.put("message", "Producto eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

}
