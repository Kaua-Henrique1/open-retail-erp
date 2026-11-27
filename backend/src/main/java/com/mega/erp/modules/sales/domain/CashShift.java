package com.mega.erp.modules.sales.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "turno_caixa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashShift {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "terminal_caixa_id", nullable = false)
    private CashTerminal cashTerminal;

    @Column(name = "utilizador_id", nullable = false)
    private UUID userId;

    @Column(name = "data_hora_abertura")
    private LocalDateTime openedAt;

    @Column(name = "data_hora_fechamento")
    private LocalDateTime closedAt;

    @Column(name = "saldo_inicial", precision = 15, scale = 2)
    private BigDecimal openingBalance;

    @Column(name = "saldo_calculado_sistema", precision = 15, scale = 2)
    private BigDecimal systemCalculatedBalance;

    @Column(name = "saldo_declarado_operador", precision = 15, scale = 2)
    private BigDecimal operatorDeclaredBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private ShiftStatus status;
}
