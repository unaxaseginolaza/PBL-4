package com.example.pbl4.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> getSales() {
        return saleService.getSales();
    }

    @PostMapping
    public ResponseEntity<Object> createSale(@RequestBody Sale sale) {
        return this.saleService.newSale(sale);
    }

    @PutMapping
    public ResponseEntity<Object> updateSale(@RequestBody Sale sale) {
        return this.saleService.newSale(sale);
    }

    @DeleteMapping(path = "/{saleId}/delete")
    public ResponseEntity<Object> deleteSale(@PathVariable("saleId")  Long id) {
        return this.saleService.deleteSale(id);
    }
}
