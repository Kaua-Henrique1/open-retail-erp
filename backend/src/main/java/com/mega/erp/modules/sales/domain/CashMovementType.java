package com.mega.erp.modules.sales.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum CashMovementType {
    SANGRIA("Sangria", BigDecimal.ONE.negate()),
    SUPRIMENTO("Suprimento", BigDecimal.ONE);

    private final String description;
    private final BigDecimal balanceMultiplier;

    public BigDecimal apply(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value.multiply(balanceMultiplier);
    }
}
