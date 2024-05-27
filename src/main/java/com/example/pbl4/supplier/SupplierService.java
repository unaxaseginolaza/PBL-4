package com.example.pbl4.supplier;

import com.example.pbl4.supplier.Supplier;
import com.example.pbl4.supplier.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSuppliers() {
        return this.supplierRepository.findAll();
    }

    public Supplier findSupplierById(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new IllegalStateException("Supplier not found"));
    }

    public void newSupplier(Supplier supplier) {
        Optional<Supplier> res = supplierRepository.findSupplierByName(supplier.getName());
        if (res.isPresent() && supplier.getId() == null) {
            throw new IllegalStateException("Ya existe un empleado con name");
        }
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) {
        boolean exists = supplierRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un Supplier con esa id");
        }
        supplierRepository.deleteById(id);
    }
}
