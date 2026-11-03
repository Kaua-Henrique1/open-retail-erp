package com.mega.erp.modules.stock.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movimentacao_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "filial_id", nullable = false)
    private UUID branchId;

    @Column(name = "item_id", nullable = false)
    private UUID itemId;

    @Column(name = "lote_id")
    private UUID batchId;

    @Column(name = "utilizador_id", nullable = false)
    private UUID userId;

    @Column(name = "pessoa_id")
    private UUID personId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimento", nullable = false, length = 20)
    private MovementType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo_operacao", nullable = false, length = 30)
    private OperationReason reason;

    @Column(name = "documento_origem", length = 100)
    private String originDocument;

    @Column(name = "quantidade", nullable = false, precision = 10, scale = 3)
    private BigDecimal quantity;

    @Column(name = "saldo_anterior", nullable = false, precision = 10, scale = 3)
    private BigDecimal previousBalance;

    @Column(name = "saldo_atual", nullable = false, precision = 10, scale = 3)
    private BigDecimal currentBalance;

    @Column(name = "valor_unitario_movimento", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitValue;

    @Column(name = "impostos_rateados", precision = 10, scale = 2)
    private BigDecimal apportionedTaxes;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dateTime;

    // Método utilitário de negócio no próprio model
    public void applyMovement(BigDecimal currentBalance, BigDecimal quantity) {
        this.previousBalance = currentBalance;
        this.quantity = quantity;
        this.currentBalance = this.type.calculateNewBalance(currentBalance, quantity);
    }
}