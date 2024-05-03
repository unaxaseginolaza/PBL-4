package com.example.crud.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("SELECT * from Product p where p.name like '%1' ")
    Optional<Product> findProductByName(String name);
}
