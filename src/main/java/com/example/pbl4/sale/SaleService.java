package com.example.pbl4.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    HashMap<String, Object> datos;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getSales() {
        return this.saleRepository.findAll();
    }

    public ResponseEntity<Object> newSale(Sale sale) {
        Optional<Sale> res = saleRepository
                .findByCustomer_CompanyNameContainsAndPriceAndQuantity
                        (sale.getCustomer().getCompanyName(), sale.getPrice(), sale.getQuantity());
        datos = new HashMap<>();

        if (res.isPresent() && sale.getId()==null) {
            //throw new IllegalStateException("ya existe el saleo");
            datos.put("error", true);
            datos.put("message", "Ya existe un sale con ese nombre");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        if (sale.getId()!= null) {
            datos.put("message", "Se actualizo con exito");
        }
        saleRepository.save(sale);
        datos.put("data", sale);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteSale(Long id) {
        boolean exists = this.saleRepository.existsById(id);
        datos = new HashMap<>();

        if (!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un sale con esa id");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        saleRepository.deleteById(id);
        datos.put("message", "Sale eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
