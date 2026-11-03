package com.mega.erp.modules.stock.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InboundMatchStatus {

    MATCHED("Produto Encontrado", "success", true),
    NOT_FOUND("Produto Não Cadastrado", "danger", false),
    BARCODE_MISSING("Código de Barras Ausente no XML", "warning", false);

    private final String description;
    private final String badgeColor; // Dica visual para o frontend (Bootstrap/Tailwind)
    private final boolean readyForInbound;

    /**
     * Regra de negócio: Valida se o item pode ser confirmado diretamente
     * sem necessidade de cadastro/vínculo prévio.
     */
    public boolean canBeConfirmed() {
        return this.readyForInbound;
    }
}