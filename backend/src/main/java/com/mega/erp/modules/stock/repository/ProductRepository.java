package com.mega.erp.modules.stock.repository;

import com.mega.erp.modules.stock.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findByEan(String ean);
}
