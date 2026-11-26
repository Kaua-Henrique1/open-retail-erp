package com.mega.erp.modules.sales.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShiftStatus {
    ABERTO("Aberto"),
    FECHADO("Fechado"),
    COM_QUEBRA("Com quebra");

    private final String description;

    public boolean isOpen() {
        return this == ABERTO;
    }
}
