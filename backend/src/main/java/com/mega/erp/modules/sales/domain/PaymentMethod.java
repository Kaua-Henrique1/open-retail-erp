package com.mega.erp.modules.sales.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMethod {
    DINHEIRO("Dinheiro", false),
    PIX("Pix", false),
    CREDITO("Cartao de credito", true),
    DEBITO("Cartao de debito", true);

    private final String description;
    private final boolean cardPayment;

    public boolean requiresCardBrand() {
        return cardPayment;
    }
}