package com.example.pbl4.purchase;

import com.example.pbl4.purchase.Purchase;
import com.example.pbl4.purchase.PurchaseRepository;
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

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getPurchases() {
        return this.purchaseRepository.findAll();
    }

    public Purchase findPurchaseById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(() -> new IllegalStateException("Purchase not found"));
    }

    public void newPurchase(Purchase purchase) {
        Optional<Purchase> res = purchaseRepository.findBySupplier_NameContainsAndPriceAndQuantity(purchase.getSupplier().getName(), purchase.getPrice() ,purchase.getQuantity());
        if (res.isPresent() && purchase.getId() == null) {
            throw new IllegalStateException("Ya existe una compra con name");
        }
        purchaseRepository.save(purchase);
    }

    public void deletePurchase(Long id) {
        boolean exists = purchaseRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un Purchase con esa id");
        }
        purchaseRepository.deleteById(id);
    }
}
