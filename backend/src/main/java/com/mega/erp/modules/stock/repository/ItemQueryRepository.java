package com.mega.erp.modules.stock.repository;

import com.mega.erp.modules.stock.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ItemQueryRepository extends JpaRepository<Item, UUID> {

    Optional<Item> findByBarcode(String barcode);

    @Query("select i from Item i where i.barcode = :codigoBarras")
    Optional<Item> findByCodigoBarras(@Param("codigoBarras") String codigoBarras);
}
