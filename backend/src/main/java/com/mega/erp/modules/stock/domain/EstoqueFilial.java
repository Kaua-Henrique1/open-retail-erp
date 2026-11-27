package com.mega.erp.modules.stock.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "estoque_filial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstoqueFilial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // TODO: Mapear como relacionamento quando a entidade Filial for criada.
    @Column(name = "filial_id", nullable = false)
    private UUID branchId;

    @Column(name = "item_id", nullable = false)
    private UUID itemId;

    @Column(name = "saldo_atual", precision = 15, scale = 4)
    private BigDecimal currentBalance;

    @Column(name = "localizacao_fisica", length = 100)
    private String physicalLocation;
}
