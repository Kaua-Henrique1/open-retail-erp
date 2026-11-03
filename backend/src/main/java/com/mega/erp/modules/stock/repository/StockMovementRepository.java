package com.mega.erp.modules.stock.repository;

import com.mega.erp.modules.stock.domain.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {

    List<StockMovement> findByOriginDocument(String originDocument);
}