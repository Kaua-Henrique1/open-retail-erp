package com.mega.erp.modules.sales.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item_venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venda_id", nullable = false)
    private Sale sale;

    @Column(name = "item_id", nullable = false)
    private UUID itemId;

    @Column(name = "quantidade", nullable = false, precision = 15, scale = 4)
    private BigDecimal quantity;

    @Column(name = "preco_unitario_tabela", nullable = false, precision = 15, scale = 4)
    private BigDecimal tableUnitPrice;

    @Column(name = "preco_praticado", nullable = false, precision = 15, scale = 4)
    private BigDecimal chargedUnitPrice;

    @Column(name = "valor_total_item", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalItemAmount;
}
