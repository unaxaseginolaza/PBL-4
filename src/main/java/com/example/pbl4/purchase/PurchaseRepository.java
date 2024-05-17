package com.example.pbl4.purchase;

import com.example.pbl4.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Optional<Purchase> findBySupplier_NameContainsAndPriceAndQuantity(String name, Float price, Float quantity);
}
