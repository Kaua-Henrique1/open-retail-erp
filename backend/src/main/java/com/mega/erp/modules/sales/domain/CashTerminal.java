package com.mega.erp.modules.sales.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "terminal_caixa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashTerminal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "filial_id", nullable = false)
    private UUID branchId;

    @Column(name = "nome_identificador", nullable = false, length = 50)
    private String identifierName;

    @Column(name = "mac_address_uuid", length = 100)
    private String macAddressUuid;

    @Column(name = "ativo")
    private Boolean active;
}
