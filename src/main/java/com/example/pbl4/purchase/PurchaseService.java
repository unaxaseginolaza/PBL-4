package com.example.pbl4.purchase;

import com.example.pbl4.purchase.Purchase;
import com.example.pbl4.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    HashMap<String, Object> datos;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getPurchases() {
        return this.purchaseRepository.findAll();
    }

    public ResponseEntity<Object> newPurchase(Purchase purchase) {
        Optional<Purchase> res = purchaseRepository
                .findBySupplier_NameContainsAndPriceAndQuantity
                        (purchase.getSupplier().getName(), purchase.getPrice(), purchase.getQuantity());
        datos = new HashMap<>();

        if (res.isPresent() && purchase.getId()==null) {
            //throw new IllegalStateException("ya existe el purchaseo");
            datos.put("error", true);
            datos.put("message", "Ya existe un purchase con ese nombre");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        if (purchase.getId()!= null) {
            datos.put("message", "Se actualizo con exito");
        }
        purchaseRepository.save(purchase);
        datos.put("data", purchase);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deletePurchase(Long id) {
        boolean exists = this.purchaseRepository.existsById(id);
        datos = new HashMap<>();

        if (!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un purchase con esa id");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        purchaseRepository.deleteById(id);
        datos.put("message", "Purchase eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
