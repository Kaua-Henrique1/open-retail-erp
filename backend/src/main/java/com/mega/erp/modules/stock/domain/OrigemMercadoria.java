package com.mega.erp.modules.stock.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrigemMercadoria {

    NACIONAL("0", "Nacional"),
    IMPORTADO("2", "Importado (Adquirido no mercado interno)");

    private final String codigoSefaz;
    private final String descricao;
}