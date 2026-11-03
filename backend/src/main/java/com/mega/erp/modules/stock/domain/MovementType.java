package com.mega.erp.modules.stock.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum MovementType {

    ENTRADA("Entrada de Estoque", 1),
    SAIDA("Saída de Estoque", -1);

    private final String description;
    private final int multiplier;

    public BigDecimal calculateNewBalance(BigDecimal currentBalance, BigDecimal quantity) {
        if (currentBalance == null) currentBalance = BigDecimal.ZERO;
        if (quantity == null) return currentBalance;

        BigDecimal adjustment = quantity.multiply(BigDecimal.valueOf(multiplier));
        return currentBalance.add(adjustment);
    }

    public boolean isInbound() {
        return this == ENTRADA;
    }

    public boolean isOutbound() {
        return this == SAIDA;
    }
}