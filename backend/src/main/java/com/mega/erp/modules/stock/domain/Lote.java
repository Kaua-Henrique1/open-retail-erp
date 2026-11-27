package com.mega.erp.modules.stock.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "lote")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "item_id", nullable = false)
    private UUID itemId;

    @Column(name = "numero_lote", nullable = false, length = 50)
    private String batchNumber;

    @Column(name = "data_fabricacao")
    private LocalDate manufacturingDate;

    @Column(name = "data_validade")
    private LocalDate expirationDate;

    @Column(name = "quantidade_original", nullable = false, precision = 15, scale = 4)
    private BigDecimal originalQuantity;

    @Column(name = "saldo_disponivel", nullable = false, precision = 15, scale = 4)
    private BigDecimal availableBalance;
}
