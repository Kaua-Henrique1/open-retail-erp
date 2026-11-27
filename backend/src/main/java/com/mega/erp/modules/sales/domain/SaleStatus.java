package com.mega.erp.modules.sales.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SaleStatus {
    PENDENTE("Pendente"),
    CONCLUIDA("Concluida"),
    CANCELADA("Cancelada");

    private final String description;
}