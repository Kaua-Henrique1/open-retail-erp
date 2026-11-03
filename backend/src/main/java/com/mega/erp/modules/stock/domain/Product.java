package com.mega.erp.modules.stock.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item", indexes = {
        @Index(name = "idx_item_codigo_barras", columnList = "codigo_barras")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", nullable = false, length = 150)
    private String name;

    @Column(name = "preco_venda_padrao", nullable = false, precision = 15, scale = 4)
    private BigDecimal price;

    @Column(name = "codigo_barras", length = 50)
    private String ean;

    protected Product() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getEan() {
        return ean;
    }
}
