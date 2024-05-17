package com.example.pbl4.sale;

import com.example.pbl4.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<Sale> findByCustomer_CompanyNameContainsAndPriceAndQuantity(String name, Float price, Float quantity);
}

