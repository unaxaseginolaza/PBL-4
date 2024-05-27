package com.example.pbl4.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getSales() {
        return this.saleRepository.findAll();
    }

    public Sale findSaleById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new IllegalStateException("Sale not found"));
    }

    public void newSale(Sale sale) {
        Optional<Sale> res = saleRepository.findByCustomer_CompanyNameContainsAndPriceAndQuantity(sale.getCustomer().getCompanyName(), sale.getPrice() ,sale.getQuantity());
        if (res.isPresent() && sale.getId() == null) {
            throw new IllegalStateException("Ya existe un venta con name");
        }
        saleRepository.save(sale);
    }

    public void deleteSale(Long id) {
        boolean exists = saleRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un Sale con esa id");
        }
        saleRepository.deleteById(id);
    }
}
