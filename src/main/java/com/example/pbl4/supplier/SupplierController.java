package com.example.pbl4.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getSuppliers() {
        return supplierService.getSuppliers();
    }

    @PostMapping
    public ResponseEntity<Object> createSupplier(@RequestBody Supplier supplier) {
        return this.supplierService.newSupplier(supplier);
    }

    @PutMapping
    public ResponseEntity<Object> updateSupplier(@RequestBody Supplier supplier) {
        return this.supplierService.newSupplier(supplier);
    }

    @DeleteMapping(path = "/{supplierId}/delete")
    public ResponseEntity<Object> deleteSupplier(@PathVariable("supplierId")  Long id) {
        return this.supplierService.deleteSupplier(id);
    }
}
