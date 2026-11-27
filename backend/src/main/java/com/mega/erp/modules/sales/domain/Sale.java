package com.mega.erp.modules.sales.domain;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turno_caixa_id", nullable = false)
    private CashShift cashShift;

    @Column(name = "pessoa_id")
    private UUID customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private SaleStatus status;

    @Column(name = "valor_bruto", nullable = false, precision = 15, scale = 2)
    private BigDecimal grossAmount;

    @Column(name = "descontos", precision = 15, scale = 2)
    private BigDecimal discounts;

    @Column(name = "acrescimos", precision = 15, scale = 2)
    private BigDecimal surcharges;

    @Column(name = "valor_liquido", nullable = false, precision = 15, scale = 2)
    private BigDecimal netAmount;

    @Column(name = "data_hora_venda")
    private LocalDateTime soldAt;

    @Column(name = "status_fiscal", length = 30)
    private String fiscalStatus;

    @Column(name = "chave_nfce", length = 44)
    private String nfceKey;

    @Column(name = "protocolo_autorizacao", length = 100)
    private String authorizationProtocol;

    @Column(name = "recibo_lote", length = 100)
    private String batchReceipt;

    @Builder.Default
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> items = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalePayment> payments = new ArrayList<>();
}
