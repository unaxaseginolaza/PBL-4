package com.example.pbl4.purchase;

import com.example.pbl4.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> getPurchases() {
        return purchaseService.getPurchases();
    }

    @PostMapping
    public ResponseEntity<Object> createPurchase(@RequestBody Purchase purchase) {
        return this.purchaseService.newPurchase(purchase);
    }

    @PutMapping
    public ResponseEntity<Object> updatePurchase(@RequestBody Purchase purchase) {
        return this.purchaseService.newPurchase(purchase);
    }

    @DeleteMapping(path = "/{purchaseId}/delete")
    public ResponseEntity<Object> deletePurchase(@PathVariable("purchaseId")  Long id) {
        return this.purchaseService.deletePurchase(id);
    }
}
