package com.example.pbl4.supplier;

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
    HashMap<String, Object> datos;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSuppliers() {
        return this.supplierRepository.findAll();
    }

    public ResponseEntity<Object> newSupplier(Supplier supplier) {
        Optional<Supplier> res = supplierRepository.findSupplierByName(supplier.getName());
        datos = new HashMap<>();

        if (res.isPresent() && supplier.getId()==null) {
            //throw new IllegalStateException("ya existe el suppliero");
            datos.put("error", true);
            datos.put("message", "Ya existe un supplier con ese nombre");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        if (supplier.getId()!= null) {
            datos.put("message", "Se actualizo con exito");
        }
        supplierRepository.save(supplier);
        datos.put("data", supplier);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteSupplier(Long id) {
        boolean exists = this.supplierRepository.existsById(id);
        datos = new HashMap<>();

        if (!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un supplier con esa id");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        supplierRepository.deleteById(id);
        datos.put("message", "Supplier eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
